package com.mgs.fantasi.driver;

import com.mgs.fantasi.rendering.Renderable;

public interface UINativeRenderer<T> {
	T render(Renderable renderable);

}
