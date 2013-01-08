package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingProcess;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategy;
import com.mgs.fantasi.wireframe.WireframeType;

import javax.swing.*;
import java.awt.*;

import static com.mgs.fantasi.driver.swing.JPanelRenderingProcessFactory.Content;

public class JPanelRenderingProcess implements RenderingProcess<JPanel> {
	private final JPanelCreationStrategy baseCreationStrategy;
	private final Content content;

	public JPanelRenderingProcess(JPanelCreationStrategy baseCreationStrategy, Content content) {
		this.baseCreationStrategy = baseCreationStrategy;
		this.content = content;
	}

	@Override
	public JPanel render() {
		JPanel container = baseCreationStrategy.create();
		if (content.isEmpty()) return container;

		container.setLayout(translateTypeIntoLayout(container, content.getType()));
		for (ToBeAdded<?> toBeAdded : content.getChildrenProcesses()) {
			RenderingProcess<JPanel> childRenderingProcessFactory = toBeAdded.getRenderingProcess();
			container.add(childRenderingProcessFactory.render(), toBeAdded.getSpecifics());
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
