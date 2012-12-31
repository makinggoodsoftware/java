package com.mgs.fantasi.styles;

import com.mgs.fantasi.properties.UIProperties;

import java.util.Set;

public class StyleManagerImpl implements StyleManager {
	public void applyStyle(UIProperties uiProperties, UIStyle uiStyle){
        uiProperties.setBorder(uiStyle.getBorderUIProperty().merge(uiProperties.getBorder()));
		uiProperties.setBackgroundColor(uiStyle.getBackgroundColorUIProperty().merge(uiProperties.getBackgroundColor()));
	}

	@Override
	public UIProperties applyStyles(UIProperties uiProperties, Set<UIStyle> stylesToApply) {
		UIProperties uiPropertiesWithStyles = uiProperties.copy();
		for (UIStyle style : stylesToApply) {
			applyStyle(uiPropertiesWithStyles, style);
		}
		return uiPropertiesWithStyles;
	}
}
