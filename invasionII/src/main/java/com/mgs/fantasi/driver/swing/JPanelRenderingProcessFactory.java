package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingProcess;
import com.mgs.fantasi.driver.RenderingProcessFactory;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategy;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.styles.StyleManager;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeChildElement;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class JPanelRenderingProcessFactory implements RenderingProcessFactory<JPanel> {
	private final JPanelCreationStrategyFactory jPanelCreationStrategyFactory;
	private final StyleManager styleManager;


	public JPanelRenderingProcessFactory(StyleManager styleManager, JPanelCreationStrategyFactory jPanelCreationStrategyFactory) {
		this.jPanelCreationStrategyFactory = jPanelCreationStrategyFactory;
		this.styleManager = styleManager;
	}

	@Override
	public RenderingProcess<JPanel> newRenderingProcess(Wireframe wireframe, final UIProfile uiProfile) {
		UIProperties uiPropertiesWithStylesApplied = styleManager.applyStyles(wireframe.getUiProperties(), uiProfile.findStylesFor(wireframe));
		JPanelCreationStrategy baseCreationStrategy = jPanelCreationStrategyFactory.forUIProperties(uiPropertiesWithStylesApplied, wireframe.getType());

		List<ToBeAdded<JPanel>> content = renderContent(uiProfile, wireframe);
		return new JPanelRenderingProcess(baseCreationStrategy, content);
	}

	private List<ToBeAdded<JPanel>> renderContent(UIProfile uiProfile, Wireframe from) {
		List<ToBeAdded<JPanel>> childObjects = new ArrayList<ToBeAdded<JPanel>>();

		for (WireframeChildElement wireframeChildPart : from.getContentElements()) {
			Wireframe child = wireframeChildPart.getChild();
			CollocationInfo collocationInfo = wireframeChildPart.getCollocationInfo();

			RenderingProcess<JPanel> jPanelRenderingProcess = newRenderingProcess(child, uiProfile);
			childObjects.add(new ToBeAdded<JPanel>(jPanelRenderingProcess, collocationInfo));
		}
		return childObjects;
	}

}
