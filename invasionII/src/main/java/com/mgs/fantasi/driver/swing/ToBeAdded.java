package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingProcess;
import com.mgs.fantasi.wireframe.CollocationInfo;

public class ToBeAdded<J> {
	private final CollocationInfo specifics;
	private final RenderingProcess<J> renderingProcess;

	ToBeAdded(RenderingProcess<J> renderingProcess, CollocationInfo specifics) {
		this.renderingProcess = renderingProcess;
		this.specifics = specifics;
	}

	public RenderingProcess<J> getRenderingProcess() {
		return renderingProcess;
	}

	public CollocationInfo getSpecifics() {
		return specifics;
	}
}
