package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingProcess;
import com.mgs.fantasi.driver.RenderingProcessFactory;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategy;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.driver.swing.jPanelCreation.ToBeAddedManagerFactory;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.styles.StyleManager;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.Wireframe;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class JPanelRenderingProcessFactory implements RenderingProcessFactory<JPanel> {
	private final JPanelCreationStrategyFactory jPanelCreationStrategyFactory;
	private final StyleManager styleManager;
	private final ToBeAddedManagerFactory toBeAddedManagerFactory;


	public JPanelRenderingProcessFactory(StyleManager styleManager, JPanelCreationStrategyFactory jPanelCreationStrategyFactory, ToBeAddedManagerFactory toBeAddedManagerFactory) {
		this.jPanelCreationStrategyFactory = jPanelCreationStrategyFactory;
		this.styleManager = styleManager;
		this.toBeAddedManagerFactory = toBeAddedManagerFactory;
	}

	@Override
	public RenderingProcess<JPanel> newRenderingProcess(View view, final UIProfile uiProfile) {
		Wireframe<View> from = view.buildContent();
		UIProperties uiPropertiesWithStylesApplied = styleManager.applyStyles(view.getUiProperties(), uiProfile.findStylesFor(view));
		JPanelCreationStrategy baseCreationStrategy = jPanelCreationStrategyFactory.forUIProperties(uiPropertiesWithStylesApplied, from.getType());

		return new JPanelRenderingProcess(baseCreationStrategy, createContent(uiProfile, from));
	}

	private List<ToBeAdded<?, JPanel>> createContent(UIProfile uiProfile, Wireframe<View> from) {
		List<ToBeAddedBuilder<?, JPanel>> childObjectsBuilder = toBeAddedManagerFactory.forType(from.getType()).process(from);
		List<ToBeAdded<?, JPanel>> childObjects = new ArrayList<ToBeAdded<?, JPanel>>();
		for (ToBeAddedBuilder<?, JPanel> toBeAddedBuilder : childObjectsBuilder) {
			RenderingProcess<JPanel> jPanelRenderingProcess = newRenderingProcess(toBeAddedBuilder.getView(), uiProfile);
			childObjects.add(toBeAddedBuilder.build(jPanelRenderingProcess));
		}
		return childObjects;
	}

}
