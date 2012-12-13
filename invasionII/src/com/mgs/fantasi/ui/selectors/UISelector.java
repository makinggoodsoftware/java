package com.mgs.fantasi.ui.selectors;

import com.mgs.fantasi.ui.wireframe.Renderable;

public interface UISelector {
	public boolean appliesTo(Renderable renderable);
}
