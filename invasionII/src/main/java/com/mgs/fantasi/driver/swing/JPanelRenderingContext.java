package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingContext;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategy;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.driver.swing.layoutConstruction.LayoutConstructionManager;
import com.mgs.fantasi.driver.swing.layoutConstruction.LayoutConstructionStrategy;
import com.mgs.fantasi.driver.swing.layoutConstruction.OnGoingChildAddition;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.styles.StyleManager;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.Wireframe;

import javax.swing.*;

public class JPanelRenderingContext implements RenderingContext<JPanel> {
	private final UIProfile uiProfile;
	private final LayoutConstructionManager layoutConstructionManager;
	private final StyleManager styleManager;
	private final JPanelCreationStrategyFactory jPanelCreationStrategyFactory;

	public JPanelRenderingContext(UIProfile uiProfile, LayoutConstructionManager layoutConstructionManager, StyleManager styleManager, JPanelCreationStrategyFactory jPanelCreationStrategyFactory) {
		this.uiProfile = uiProfile;
		this.layoutConstructionManager = layoutConstructionManager;
		this.styleManager = styleManager;
		this.jPanelCreationStrategyFactory = jPanelCreationStrategyFactory;
	}

	JPanelCreationInstructions createJPanelConstructionInstructions(View view) {
		UIProperties uiPropertiesWithStylesApplied = styleManager.applyStyles(view.getUiProperties(), uiProfile.findStylesFor(view));
		JPanelCreationStrategy outsideCreationStrategy = jPanelCreationStrategyFactory.forUIProperties(uiPropertiesWithStylesApplied);
		LayoutConstructionStrategy<?, ? extends Wireframe> insideConstructionStrategy = layoutConstructionManager.createAndFillLayout(view.buildContent());
		return new JPanelCreationInstructions(outsideCreationStrategy, insideConstructionStrategy);
	}

	JPanel createJPanel(JPanelCreationInstructions jPanelCreationInstructions) {
		JPanel container = jPanelCreationInstructions.getOutsideCreationStrategy().create();
		LayoutConstructionStrategy<?, ? extends Wireframe> insideConstructionStrategy = jPanelCreationInstructions.getInsideConstructionStrategy();
		if (insideConstructionStrategy.isEmpty()) return container;

		container.setLayout(insideConstructionStrategy.getLayoutManager(container));
		for (OnGoingChildAddition onGoingChildAddition : insideConstructionStrategy.getToBeAdded()) {
			container.add(render(onGoingChildAddition.getContent()), onGoingChildAddition.getSpecifics());
		}
		return container;
	}

	@Override
	public JPanel render(View view) {
		JPanelCreationInstructions jPanelConstructionInstructions = createJPanelConstructionInstructions(view);
		return createJPanel(jPanelConstructionInstructions);
	}
}