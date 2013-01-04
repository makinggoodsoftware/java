package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingContext;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategy;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.driver.swing.layoutConstruction.LayoutConstructionManager;
import com.mgs.fantasi.driver.swing.layoutConstruction.LayoutConstructionStrategy;
import com.mgs.fantasi.driver.swing.layoutConstruction.ToBeAddedWithSpecifics;
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

	@Override
	public JPanel render(View view) {
		UIProperties uiPropertiesWithStylesApplied = styleManager.applyStyles(view.getUiProperties(), uiProfile.findStylesFor(view));
		JPanelCreationStrategy outsideCreationStrategy = jPanelCreationStrategyFactory.forUIProperties(uiPropertiesWithStylesApplied);
		JPanel container = outsideCreationStrategy.create();
		Wireframe<View> content = view.buildContent();
		if (content.isEmpty()) return container;

		return processContent(container, content);
	}

	private JPanel processContent(JPanel container, Wireframe<View> content) {
		LayoutConstructionStrategy<?, ? extends Wireframe> insideConstructionStrategy = layoutConstructionManager.createAndFillLayout(content);
		container.setLayout(insideConstructionStrategy.getLayoutManager(container));
		for (ToBeAddedWithSpecifics toBeAddedWithSpecifics : insideConstructionStrategy.getToBeAddedWithSpecifics()) {
			container.add(render(toBeAddedWithSpecifics.getContent()), toBeAddedWithSpecifics.getSpecifics());
		}
		return container;
	}
}
