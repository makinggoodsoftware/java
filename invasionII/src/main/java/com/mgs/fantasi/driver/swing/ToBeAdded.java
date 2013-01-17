package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingProcess;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.WireframeChildElement;

public class ToBeAdded<J> {
	private final WireframeChildElement wireframeChildPart;
	private final RenderingProcess<J> renderingProcess;

	ToBeAdded(RenderingProcess<J> renderingProcess, WireframeChildElement wireframeChildPart) {
		this.renderingProcess = renderingProcess;
		this.wireframeChildPart = wireframeChildPart;
	}

	public RenderingProcess<J> getRenderingProcess() {
		return renderingProcess;
	}

	public CollocationInfo getSpecifics() {
		return wireframeChildPart.getCollocationInfo();
	}
}
