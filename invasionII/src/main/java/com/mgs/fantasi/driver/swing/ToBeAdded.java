package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingProcess;
import com.mgs.fantasi.wireframe.Placeholder;
import com.mgs.fantasi.wireframe.Wireframe;

public class ToBeAdded<J> {
	private final Placeholder<Wireframe> specifics;
	private final RenderingProcess<J> renderingProcess;

	ToBeAdded(RenderingProcess<J> renderingProcess, Placeholder<Wireframe> specifics) {
		this.renderingProcess = renderingProcess;
		this.specifics = specifics;
	}

	public RenderingProcess<J> getRenderingProcess() {
		return renderingProcess;
	}

	public Placeholder<Wireframe> getSpecifics() {
		return specifics;
	}
}
