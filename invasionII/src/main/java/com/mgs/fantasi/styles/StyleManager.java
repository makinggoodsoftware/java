package com.mgs.fantasi.styles;

import com.mgs.fantasi.properties.UIProperties;

import java.util.Set;

public interface StyleManager {
	void applyStyle(UIProperties uiProperties, UIStyle uiStyle);

	UIProperties applyStyles(UIProperties uiProperties, Set<UIStyle> stylesToApply);
}
