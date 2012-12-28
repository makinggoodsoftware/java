package com.mgs.fantasi.driver;

import com.mgs.fantasi.styles.UIProfileFactory;
import com.mgs.fantasi.views.View;

public interface UINativeRenderer<T> {
	T render(View renderable, UIProfileFactory uiProfileFactory);

}
