package com.mgs.fantasi.driver;

import com.mgs.fantasi.views.View;

public interface RenderingContext<T> {
	T render(View view);
}
