package com.mgs.fantasi.properties;

import com.mgs.fantasi.properties.data.Border;
import com.mgs.fantasi.properties.data.Color;
import com.mgs.fantasi.properties.data.Padding;
import com.mgs.fantasi.properties.data.measurements.Measurement;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;

public interface UIPropertiesReader {
	UIProperty<Border> getBorder();

	UIProperty<Color> getBackgroundColor();

	UIProperty<Color> getForegroundColor();

	UIProperty<Padding> getPadding();

	UIProperty<PolygonPointsIterator> getShape();

	UIProperty<Measurement> getMeasurement();
}
