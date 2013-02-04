package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.properties.Border;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.properties.UIProperty;
import com.mgs.fantasi.properties.measurements.Fraction;

import javax.swing.*;
import java.awt.*;

public class SwingUtils {
	public static void applyUIProperties(JPanel jPanel, UIProperties uiProperties) {
		UIProperty<com.mgs.fantasi.properties.Color> backgroundColor = uiProperties.getBackgroundColor();
		if (backgroundColor.isDefined() && !backgroundColor.isEmpty()) {
			jPanel.setBackground(backgroundColor.getValue().getColorAsAwt());
		}
		UIProperty<Border> border = uiProperties.getBorder();
		if (border.isFullyDefined() && border.getValue().getWidth() > 0) {
			UIProperty<com.mgs.fantasi.properties.Color> colorProviderUI = border.getValue().getColor();
			if (colorProviderUI.isDefined()) {
				com.mgs.fantasi.properties.Color colorData = colorProviderUI.getValue();
				Color lineColor = colorProviderUI.isEmpty() ? Color.ORANGE : colorData.getColorAsAwt();
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
