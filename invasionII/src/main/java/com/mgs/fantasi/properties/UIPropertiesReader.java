package com.mgs.fantasi.properties;

import com.mgs.fantasi.properties.measurements.Measurement;
import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;

public interface UIPropertiesReader {
	UIProperty<Border> getBorder();

	UIProperty<Color> getBackgroundColor();

	UIProperty<Color> getForegroundColor();

	UIProperty<Padding> getPadding();

	UIProperty<PolygonPointsIterator> getShape();

	UIProperty<Measurement> getMeasurement();
}
