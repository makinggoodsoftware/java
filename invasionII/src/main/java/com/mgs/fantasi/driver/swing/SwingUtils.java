package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.properties.ColorFactory;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.properties.UIPropertyProvider;
import com.mgs.fantasi.properties.measurements.Fraction;

import javax.swing.*;
import java.awt.*;

public class SwingUtils {
	public static void applyUIProperties(JPanel jPanel, UIProperties uiProperties) {
		UIPropertyProvider<ColorFactory.Color> backgroundColor = uiProperties.getBackgroundColor();
		if (backgroundColor.isDefined() && !backgroundColor.getValue().isTransparent()) {
			jPanel.setBackground(backgroundColor.getValue().getColorAsAwt());
		}
		UIPropertyProvider<com.mgs.fantasi.properties.BorderFactory.Border> border = uiProperties.getBorder();
		if (border.isDefined() && border.getValue().getWidth() > 0) {
			UIPropertyProvider<ColorFactory.Color> colorProviderUI = border.getValue().getColor();
			if (colorProviderUI.isDefined()) {
				ColorFactory.Color colorData = colorProviderUI.getValue();
				Color lineColor = colorData.isTransparent() ? Color.ORANGE : colorData.getColorAsAwt();
				javax.swing.border.Border lineBorder = BorderFactory.createLineBorder(lineColor, border.getValue().getWidth());
				jPanel.setBorder(lineBorder);
			} else {
				throw new RuntimeException("Can't paint the border without a color!!!");
			}

		} else {
			jPanel.setBorder(null);
		}
	}

	public static GridBagConstraints coordinates(int x, int y, Fraction widthSizeRatio, Fraction heightSizeRatio) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = widthSizeRatio.toDouble();
		gbc.weighty = heightSizeRatio.toDouble();
		gbc.fill = GridBagConstraints.BOTH;
		return gbc;
	}
}
