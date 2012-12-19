package com.mgs.fantasi.properties.selectors;

import com.mgs.fantasi.rendering.Renderable;
import com.mgs.fantasi.views.View;

public interface UISelector {
	public boolean appliesTo(View renderable);
}
