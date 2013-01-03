package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.styles.UIProfile;

public interface RenderingContextFactory<T> {
	RenderingContext<T> getContextFor(UIProfile uiProfile);
}
