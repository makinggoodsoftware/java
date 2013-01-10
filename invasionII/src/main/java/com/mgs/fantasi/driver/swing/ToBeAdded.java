package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingProcess;
import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.Placeholder;

public class ToBeAdded<J> {
	private final Placeholder<View> specifics;
	private final RenderingProcess<J> renderingProcess;

	ToBeAdded(RenderingProcess<J> renderingProcess, Placeholder<View> specifics) {
		this.renderingProcess = renderingProcess;
		this.specifics = specifics;
	}

	public RenderingProcess<J> getRenderingProcess() {
		return renderingProcess;
	}

	public Placeholder<View> getSpecifics() {
		return specifics;
	}
}
