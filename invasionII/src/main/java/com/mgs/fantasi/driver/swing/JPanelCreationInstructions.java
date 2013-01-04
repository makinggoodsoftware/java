package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategy;
import com.mgs.fantasi.wireframe.Wireframe;

public class JPanelCreationInstructions {
	private final JPanelCreationStrategy containerCreationStrategy;
	private final Wireframe content;

	public JPanelCreationInstructions(JPanelCreationStrategy containerCreationStrategy, Wireframe content) {
		this.containerCreationStrategy = containerCreationStrategy;
		this.content = content;
	}

	public JPanelCreationStrategy getContainerCreationStrategy() {
		return containerCreationStrategy;
	}

	public Wireframe getContent() {
		return content;
	}
}
