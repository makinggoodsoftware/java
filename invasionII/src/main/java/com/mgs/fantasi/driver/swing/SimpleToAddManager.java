package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.RectangleWireframe;
import com.mgs.fantasi.wireframe.Wireframe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.mgs.fantasi.driver.swing.SwingUtils.coordinates;
import static com.mgs.fantasi.properties.measurements.Fractions.all;

public class SimpleToAddManager implements ToBeAddedManager {
	@Override
	public List<ToBeAddedBuilder> process(Wireframe<View> from) {
		final List<ToBeAddedBuilder> toBeAddedBuilderList1 = new ArrayList<ToBeAddedBuilder>();
		RectangleWireframe<View> rectangle = (RectangleWireframe<View>) from;
		if (rectangle.getContent() != null) {
			ToBeAddedBuilder<GridBagConstraints, JPanel> toBeAddedBuilder = new ToBeAddedBuilder<GridBagConstraints, JPanel>(coordinates(0, 0, all(), all()), rectangle.getContent());
			toBeAddedBuilderList1.add(toBeAddedBuilder);
		}
		return toBeAddedBuilderList1;
	}
}
