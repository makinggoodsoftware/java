package com.mgs.fantasi.driver;

import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.views.View;

public interface UINativeRenderer<T> {
	T render(View renderable, UIProfile uiProfile);

}
