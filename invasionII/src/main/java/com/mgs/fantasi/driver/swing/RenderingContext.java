package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.views.View;

public interface RenderingContext<T> {
	T render(View view);
}
