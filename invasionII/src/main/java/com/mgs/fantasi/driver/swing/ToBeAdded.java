package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingProcess;

public class ToBeAdded<T, J> {
	private final T specifics;
	private final RenderingProcess<J> renderingProcess;

	ToBeAdded(RenderingProcess<J> renderingProcess, T specifics) {
		this.renderingProcess = renderingProcess;
		this.specifics = specifics;
	}

	public RenderingProcess<J> getRenderingProcess() {
		return renderingProcess;
	}

	public T getSpecifics() {
		return specifics;
	}
}
