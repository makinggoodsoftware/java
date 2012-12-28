package com.mgs.fantasi.styles;

import com.mgs.fantasi.properties.UIProperties;

public class StyleManager {
	public static void applyStyle(UIStyle uiStyle, UIProperties uiProperties){
        uiProperties.setBorder(uiStyle.getBorderUIProperty().merge(uiProperties.getBorder()));
		uiProperties.setBackgroundColor(uiStyle.getBackgroundColorUIProperty().merge(uiProperties.getBackgroundColor()));
	}
}
