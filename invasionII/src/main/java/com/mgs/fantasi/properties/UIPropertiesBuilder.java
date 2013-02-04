package com.mgs.fantasi.properties;

import com.mgs.fantasi.properties.measurements.Measurement;
import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;

public interface UIPropertiesBuilder {
	UIPropertiesBuilder withUIProperty(UIProperty<? extends UIPropertyData> propertyToModify);

	UIPropertiesBuilder withPadding(UIProperty<Padding> padding);

	UIPropertiesBuilder withShape(UIProperty<PolygonPointsIterator> polygonPointsIterator);

	UIPropertiesBuilder withMeasurement(UIProperty<Measurement> measurement);

	UIPropertiesBuilder withBorder(UIProperty<Border> border);

	UIPropertiesBuilder withBackgroundColor(UIProperty<Color> color);

	UIProperties build();

	UIPropertiesBuilder withUIProperties(UIProperties uiProperties);
}
