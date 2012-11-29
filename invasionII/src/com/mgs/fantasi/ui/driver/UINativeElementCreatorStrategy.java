package com.mgs.fantasi.ui.driver;

import com.mgs.fantasi.ui.wireframe.Wireframe;
import com.mgs.fantasi.ui.profile.SizeStrategy;
import com.mgs.fantasi.ui.profile.UIStyle;

import java.util.Set;

public interface UINativeElementCreatorStrategy<T> {
	T createSkeleton(Wireframe wireframe, Set<UIStyle> uiStyles);

	void compose(T parent, T child, SizeStrategy sizeStrategy, int x, int y);
}
