package com.mgs.fantasi.properties.selectors;

import com.mgs.fantasi.rendering.Renderable;

public interface UISelector {
	public boolean appliesTo(Renderable renderable);
}
