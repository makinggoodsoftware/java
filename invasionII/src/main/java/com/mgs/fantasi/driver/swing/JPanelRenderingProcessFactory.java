package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingProcess;
import com.mgs.fantasi.driver.RenderingProcessFactory;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategy;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.styles.StyleManager;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.wireframe.Placeholder;
import com.mgs.fantasi.wireframe.Wireframe;

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

		List<ToBeAdded<JPanel>> content = createContent(uiProfile, wireframe);
		return new JPanelRenderingProcess(baseCreationStrategy, content);
	}

	private List<ToBeAdded<JPanel>> createContent(UIProfile uiProfile, Wireframe from) {
		List<ToBeAdded<JPanel>> childObjects = new ArrayList<ToBeAdded<JPanel>>();

		for (Placeholder<Wireframe> viewPlaceholder : from.getContent()) {
			Wireframe content = viewPlaceholder.getContent();
			UIProperties uiPropertiesWithStylesApplied = styleManager.applyStyles(content.getUiProperties(), uiProfile.findStylesFor(content));
			JPanelCreationStrategy baseCreationStrategy = jPanelCreationStrategyFactory.forUIProperties(uiPropertiesWithStylesApplied, content.getType());

			List<ToBeAdded<JPanel>> content1 = createContent(uiProfile, content);
			RenderingProcess<JPanel> jPanelRenderingProcess = new JPanelRenderingProcess(baseCreationStrategy, content1);
			childObjects.add(new ToBeAdded<JPanel>(jPanelRenderingProcess, viewPlaceholder));
		}
		return childObjects;
	}

}
