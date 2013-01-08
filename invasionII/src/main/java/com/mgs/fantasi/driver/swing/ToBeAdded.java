package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingProcess;

import javax.swing.*;

public class ToBeAdded<T> {
	private final T specifics;
	private final RenderingProcess<JPanel> renderingProcess;

	public ToBeAdded(T specifics, RenderingProcess<JPanel> renderingProcess) {
		this.specifics = specifics;
		this.renderingProcess = renderingProcess;
	}

	public RenderingProcess<JPanel> getRenderingProcess() {
		return renderingProcess;
	}

	public T getSpecifics() {
		return specifics;
	}
}
