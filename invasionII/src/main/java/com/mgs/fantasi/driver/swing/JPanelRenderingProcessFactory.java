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
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeFactory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class JPanelRenderingProcessFactory implements RenderingProcessFactory<JPanel> {
	private final JPanelCreationStrategyFactory jPanelCreationStrategyFactory;
	private final StyleManager styleManager;
	private final WireframeFactory<View> wireframeFactory;


	public JPanelRenderingProcessFactory(StyleManager styleManager, JPanelCreationStrategyFactory jPanelCreationStrategyFactory, WireframeFactory<View> wireframeFactory) {
		this.jPanelCreationStrategyFactory = jPanelCreationStrategyFactory;
		this.styleManager = styleManager;
		this.wireframeFactory = wireframeFactory;
	}

	@Override
	public RenderingProcess<JPanel> newRenderingProcess(View view, final UIProfile uiProfile) {
		Wireframe<View> from = view.buildLayout(wireframeFactory);
		UIProperties uiPropertiesWithStylesApplied = styleManager.applyStyles(view.getUiProperties(), uiProfile.findStylesFor(view));
		JPanelCreationStrategy baseCreationStrategy = jPanelCreationStrategyFactory.forUIProperties(uiPropertiesWithStylesApplied, from.getType());

		List<ToBeAdded<JPanel>> content = createContent(uiProfile, from);
		return new JPanelRenderingProcess(baseCreationStrategy, content);
	}

	private List<ToBeAdded<JPanel>> createContent(UIProfile uiProfile, Wireframe<View> from) {
		List<ToBeAdded<JPanel>> childObjects = new ArrayList<ToBeAdded<JPanel>>();

		for (Placeholder<View> viewPlaceholder : from.getContent()) {
			View content = viewPlaceholder.getContent();
			RenderingProcess<JPanel> jPanelRenderingProcess = newRenderingProcess(content, uiProfile);
			childObjects.add(new ToBeAdded<JPanel>(jPanelRenderingProcess, viewPlaceholder));
		}
		return childObjects;
	}

}
