package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategy;
import com.mgs.fantasi.driver.swing.layoutProvider.LayoutProvider;

import javax.swing.*;
import java.util.Map;

public class JPanelRenderingProcess implements RenderingProcess<JPanel> {
	private final JPanelCreationStrategy baseCreationStrategy;
	private final Map<Object, RenderingProcess<JPanel>> childrenProcesses;
	private final LayoutProvider layoutProvider;

	public JPanelRenderingProcess(JPanelCreationStrategy baseCreationStrategy, Map<Object, RenderingProcess<JPanel>> childrenProcesses, LayoutProvider layoutProvider) {
		this.baseCreationStrategy = baseCreationStrategy;
		this.childrenProcesses = childrenProcesses;
		this.layoutProvider = layoutProvider;
	}

	@Override
	public JPanel render() {
		JPanel container = baseCreationStrategy.create();
		if (layoutProvider.isEmpty()) return container;

		container.setLayout(layoutProvider.getLayoutManager(container));

		for (Object specifics : childrenProcesses.keySet()) {
			RenderingProcess<JPanel> childRenderingProcessFactory = childrenProcesses.get(specifics);
			container.add(childRenderingProcessFactory.render(), specifics);
		}
		return container;
	}
}
