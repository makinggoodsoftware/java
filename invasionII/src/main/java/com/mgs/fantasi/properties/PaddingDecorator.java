package com.mgs.fantasi.properties;

import com.mgs.fantasi.properties.data.Padding;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.properties.data.measurements.Measurement;
import com.mgs.fantasi.properties.data.measurements.Measurements;

import javax.swing.*;
import java.awt.*;

import static com.mgs.fantasi.driver.swing.SwingUtils.coordinates;

public class PaddingDecorator implements JPanelDecorator<Padding> {

	public static int resolveMeasurement(Measurement measurement) {
		if (measurement instanceof Measurements.SimpleMeasurement) {
			return ((Measurements.SimpleMeasurement) measurement).getIntValue();
		}
		return 0;
	}

	@Override
	public JPanel decorate(JPanel nativeElement, Padding padding) {
		if (padding.isEmpty()) {
			return nativeElement;
		}
		JPanel decorated = new JPanel();
		decorated.setOpaque(false);
		decorated.setLayout(new GridBagLayout());
		int top = resolveMeasurement(padding.getTop());
		int right = resolveMeasurement(padding.getRight());
		int bottom = resolveMeasurement(padding.getBottom());
		int left = resolveMeasurement(padding.getLeft());
		decorated.setBorder(BorderFactory.createEmptyBorder(top, right, bottom, left));
		decorated.add(nativeElement, coordinates(0, 0, Fractions.all(), Fractions.all()));
		return decorated;
	}
}
