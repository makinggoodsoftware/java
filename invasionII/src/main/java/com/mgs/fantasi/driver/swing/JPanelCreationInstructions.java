package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategy;
import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.Wireframe;

public class JPanelCreationInstructions {
	private final JPanelCreationStrategy containerCreationStrategy;
	private final Wireframe<View> content;

	public JPanelCreationInstructions(JPanelCreationStrategy containerCreationStrategy, Wireframe<View> content) {
		this.containerCreationStrategy = containerCreationStrategy;
		this.content = content;
	}

	public JPanelCreationStrategy getContainerCreationStrategy() {
		return containerCreationStrategy;
	}

	public Wireframe<View> getContent() {
		return content;
	}
}
