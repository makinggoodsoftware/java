package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingProcess;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategy;
import com.mgs.fantasi.wireframe.WireframeType;

import javax.swing.*;
import java.awt.*;

public class JPanelRenderingProcess implements RenderingProcess<JPanel> {
	private final JPanelCreationStrategy baseCreationStrategy;
	private final RenderingContent renderingContent;

	public JPanelRenderingProcess(JPanelCreationStrategy baseCreationStrategy, RenderingContent renderingContent) {
		this.baseCreationStrategy = baseCreationStrategy;
		this.renderingContent = renderingContent;
	}

	@Override
	public JPanel render() {
		JPanel container = baseCreationStrategy.create();
		if (renderingContent.isEmpty()) return container;

		container.setLayout(translateTypeIntoLayout(container, baseCreationStrategy.getType()));
		for (ToBeAdded<?, JPanel> toBeAdded : renderingContent.getChildrenProcesses()) {
			RenderingProcess<JPanel> childRenderingProcess = toBeAdded.getRenderingProcess();
			container.add(childRenderingProcess.render(), toBeAdded.getSpecifics());
		}
		return container;
	}

	private LayoutManager translateTypeIntoLayout(JPanel container, WireframeType type) {
		switch (type) {
			case GRID:
			case SIMPLE:
				return new GridBagLayout();
			case LAYERS:
				return new OverlayLayout(container);
			default:
				throw new RuntimeException("Shouldn't have reached this point in the code!!!");
		}
	}
}
