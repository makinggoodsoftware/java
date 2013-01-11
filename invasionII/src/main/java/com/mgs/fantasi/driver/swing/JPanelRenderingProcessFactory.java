package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingProcess;
import com.mgs.fantasi.driver.RenderingProcessFactory;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategy;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.styles.StyleManager;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.Placeholder;
import com.mgs.fantasi.wireframe.PlaceholderFactory;
import com.mgs.fantasi.wireframe.Wireframe;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class JPanelRenderingProcessFactory implements RenderingProcessFactory<JPanel> {
	private final JPanelCreationStrategyFactory jPanelCreationStrategyFactory;
	private final StyleManager styleManager;
	private final PlaceholderFactory placeholderFactory;


	public JPanelRenderingProcessFactory(StyleManager styleManager, JPanelCreationStrategyFactory jPanelCreationStrategyFactory, PlaceholderFactory placeholderFactory) {
		this.jPanelCreationStrategyFactory = jPanelCreationStrategyFactory;
		this.styleManager = styleManager;
		this.placeholderFactory = placeholderFactory;
	}

	@Override
	public RenderingProcess<JPanel> newRenderingProcess(View view, final UIProfile uiProfile) {
		Wireframe<View> from = view.buildContent(placeholderFactory);
		UIProperties uiPropertiesWithStylesApplied = styleManager.applyStyles(view.getUiProperties(), uiProfile.findStylesFor(view));
		JPanelCreationStrategy baseCreationStrategy = jPanelCreationStrategyFactory.forUIProperties(uiPropertiesWithStylesApplied, from.getType());

		List<ToBeAdded<JPanel>> content = createContent(uiProfile, from);
		return new JPanelRenderingProcess(baseCreationStrategy, content);
	}

	private List<ToBeAdded<JPanel>> createContent(UIProfile uiProfile, Wireframe<View> from) {
		List<ToBeAdded<JPanel>> childObjects = new ArrayList<ToBeAdded<JPanel>>();
		for (Placeholder<View> viewPlaceholder : from.getContent()) {
			RenderingProcess<JPanel> jPanelRenderingProcess = newRenderingProcess(viewPlaceholder.getContent(), uiProfile);
			childObjects.add(new ToBeAdded<JPanel>(jPanelRenderingProcess, viewPlaceholder));

		}
		return childObjects;
	}

}
