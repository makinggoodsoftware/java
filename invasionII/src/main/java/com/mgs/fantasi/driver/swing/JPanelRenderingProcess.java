package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingProcess;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategy;
import com.mgs.fantasi.wireframe.WireframeType;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JPanelRenderingProcess implements RenderingProcess<JPanel> {
	private final JPanelCreationStrategy baseCreationStrategy;
	private final List<ToBeAdded<?, JPanel>> renderingContent;

	public JPanelRenderingProcess(JPanelCreationStrategy baseCreationStrategy, List<ToBeAdded<?, JPanel>> renderingContent) {
		this.baseCreationStrategy = baseCreationStrategy;
		this.renderingContent = renderingContent;
	}

	@Override
	public JPanel render() {
		JPanel container = baseCreationStrategy.create();
		if (renderingContent.isEmpty()) return container;

		container.setLayout(translateTypeIntoLayout(container, baseCreationStrategy.getType()));
		for (ToBeAdded<?, JPanel> toBeAdded : renderingContent) {
			RenderingProcess<JPanel> childRenderingProcess = toBeAdded.getRenderingProcess();
			Object specifics = toBeAdded.getSpecifics();

			JPanel child = childRenderingProcess.render();
			container.add(child, specifics);
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
