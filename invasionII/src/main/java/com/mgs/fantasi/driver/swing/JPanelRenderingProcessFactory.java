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
import com.mgs.fantasi.wireframe.WireframeType;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static com.mgs.fantasi.driver.swing.ToBeAddedBuilder.ToBeAdded;

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

		JPanelCreationStrategy baseCreationStrategy = jPanelCreationStrategyFactory.forUIProperties(uiPropertiesWithStylesApplied);
		Content content = createContent(uiProfile, from);

		return new JPanelRenderingProcess(baseCreationStrategy, content);
	}

	private Content createContent(UIProfile uiProfile, Wireframe<View> from) {
		List<ToBeAddedBuilder<?, JPanel>> childObjectsBuilder = toBeAddedManagerFactory.forType(from.getType()).process(from);
		List<ToBeAdded<?, JPanel>> childObjects = new ArrayList<ToBeAdded<?, JPanel>>();
		for (ToBeAddedBuilder<?, JPanel> toBeAddedBuilder : childObjectsBuilder) {
			RenderingProcess<JPanel> jPanelRenderingProcess = newRenderingProcess(toBeAddedBuilder.getView(), uiProfile);
			childObjects.add(toBeAddedBuilder.build(jPanelRenderingProcess));
		}
		return new Content(childObjects, from.getType());
	}

	public static class Content {
		private final List<ToBeAddedBuilder.ToBeAdded<?, JPanel>> childrenProcesses;
		private final WireframeType wireframeType;

		public Content(List<ToBeAdded<?, JPanel>> childrenProcesses, WireframeType wireframeType) {
			this.childrenProcesses = childrenProcesses;
			this.wireframeType = wireframeType;
		}

		public List<ToBeAdded<?, JPanel>> getChildrenProcesses() {
			return childrenProcesses;
		}

		public boolean isEmpty() {
			return childrenProcesses.size() == 0;
		}

		public WireframeType getType() {
			return wireframeType;
		}
	}
}
