package com.mgs.fantasi.styles;

import com.mgs.fantasi.properties.UIProperties;

import java.util.Set;

//TODO this should have tests... They are currently implemented inside UIPropertiesTest
public class StyleManagerImpl implements StyleManager {
	//TODO this should not be part of the interface
	@Override
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
