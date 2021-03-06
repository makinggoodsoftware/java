package com.mgs.fantasi.properties;

import com.mgs.fantasi.properties.data.Border;
import com.mgs.fantasi.properties.data.Color;
import com.mgs.fantasi.properties.data.Padding;
import com.mgs.fantasi.properties.data.measurements.Measurement;

public interface UIPropertiesProvider {
	UIProperty<Border> getBorder();

	UIProperty<Color> getBackgroundColor();

	UIProperty<Color> getForegroundColor();

	UIProperty<Padding> getPadding();

	UIProperty<Measurement> getMeasurement();
}
