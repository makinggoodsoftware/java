package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingProcess;
import com.mgs.fantasi.views.View;

public class ToBeAddedBuilder<T, J> {
	private final T specifics;
	private final View renderingProcess;

	public ToBeAddedBuilder(T specifics, View renderingProcess) {
		this.specifics = specifics;
		this.renderingProcess = renderingProcess;
	}

	public View getView() {
		return renderingProcess;
	}

	public ToBeAdded<T, J> build(RenderingProcess<J> renderingProcess) {
		return new ToBeAdded<T, J>(renderingProcess, specifics);
	}

}
