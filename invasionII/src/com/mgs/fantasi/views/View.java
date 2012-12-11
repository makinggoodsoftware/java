package com.mgs.fantasi.views;

import com.mgs.fantasi.ui.wireframe.Wireframe;

public interface View {
	public Wireframe render();
	public View newCopy();
}
