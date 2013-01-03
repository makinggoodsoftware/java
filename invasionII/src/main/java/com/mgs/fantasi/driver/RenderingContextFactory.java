package com.mgs.fantasi.driver;

import com.mgs.fantasi.styles.UIProfile;

public interface RenderingContextFactory<T> {
	RenderingContext<T> getContextFor(UIProfile uiProfile);
}
