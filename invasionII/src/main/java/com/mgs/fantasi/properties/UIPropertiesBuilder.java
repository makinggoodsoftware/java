package com.mgs.fantasi.properties;

import com.mgs.fantasi.properties.measurements.Measurement;
import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;

import java.util.HashMap;
import java.util.Map;

import static com.mgs.fantasi.properties.UIPropertyId.*;

public class UIPropertiesBuilder {
	private final Map<UIPropertyId, UIProperty<? extends UIPropertyData>> properties;

	public UIPropertiesBuilder(UIPropertiesReader initializationStrategy) {
		properties = propertiesAsMap(initializationStrategy);
	}

	public UIPropertiesBuilder withUIProperty(UIProperty<? extends UIPropertyData> propertyToModify) {
		if (propertyToModify.isNotDefined()) return this;

		switch (propertyToModify.getType()) {
			case SHAPE:
				properties.put(SHAPE, (UIProperty<PolygonPointsIterator>) propertyToModify);
				break;
			case PADDING:
				properties.put(PADDING, (UIProperty<Padding>) propertyToModify);
				break;
			case BORDER:
				properties.put(BORDER, (UIProperty<Border>) propertyToModify);
				break;
			case BACKGROUND_COLOR:
				properties.put(BACKGROUND_COLOR, (UIProperty<Color>) propertyToModify);
				break;
			case FOREGROUND_COLOR:
				properties.put(FOREGROUND_COLOR, (UIProperty<Color>) propertyToModify);
				break;
			case MEASUREMENT:
				properties.put(MEASUREMENT, (UIProperty<Measurement>) propertyToModify);
				break;
			default:
				throw new RuntimeException("Unexpected line of code reached");
		}
		return this;
	}

	public UIPropertiesBuilder withPadding(UIProperty<Padding> padding) {
		return withUIProperty(padding);
	}

	public UIPropertiesBuilder withShape(UIProperty<PolygonPointsIterator> polygonPointsIterator) {
		return withUIProperty(polygonPointsIterator);
	}

	public UIPropertiesBuilder withMeasurement(UIProperty<Measurement> measurement) {
		return withUIProperty(measurement);
	}

	public UIPropertiesBuilder withBorder(UIProperty<Border> border) {
		return withUIProperty(border);
	}

	public UIPropertiesBuilder withBackgroundColor(UIProperty<Color> color) {
		return withUIProperty(color);
	}

	public UIPropertiesBuilder withUIProperties(UIProperties uiProperties) {
		for (UIProperty<? extends UIPropertyData> uiProperty : uiProperties) {
			withUIProperty(uiProperty);
		}
		return this;
	}

	public UIProperties build() {
		return new UIProperties(properties);
	}

	private Map<UIPropertyId, UIProperty<? extends UIPropertyData>> propertiesAsMap(UIPropertiesReader initializationStrategy) {
		Map<UIPropertyId, UIProperty<? extends UIPropertyData>> properties = new HashMap<UIPropertyId, UIProperty<? extends UIPropertyData>>();
		properties.put(BORDER, initializationStrategy.getBorder());
		properties.put(BACKGROUND_COLOR, initializationStrategy.getBackgroundColor());
		properties.put(FOREGROUND_COLOR, initializationStrategy.getForegroundColor());
		properties.put(PADDING, initializationStrategy.getPadding());
		properties.put(SHAPE, initializationStrategy.getShape());
		properties.put(MEASUREMENT, initializationStrategy.getMeasurement());
		return properties;
	}
}
