package com.mgs.fantasi.selectors;

import com.mgs.fantasi.wireframe.Wireframe;

public interface UISelector {
	public boolean appliesTo(Wireframe renderable);
}
