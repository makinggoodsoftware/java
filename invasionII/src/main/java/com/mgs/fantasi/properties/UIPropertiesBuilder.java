package com.mgs.fantasi.properties;

import com.mgs.fantasi.properties.data.Border;
import com.mgs.fantasi.properties.data.Color;
import com.mgs.fantasi.properties.data.Padding;
import com.mgs.fantasi.properties.data.UIPropertyData;
import com.mgs.fantasi.properties.data.measurements.Measurement;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;

import java.util.Map;
import java.util.Set;

import static com.mgs.fantasi.properties.UIPropertyId.*;

public class UIPropertiesBuilder {
	private final Map<UIPropertyId, UIProperty<? extends UIPropertyData>> properties;

	public UIPropertiesBuilder(Map<UIPropertyId, UIProperty<? extends UIPropertyData>> properties) {
		this.properties = properties;
	}

	public UIPropertiesBuilder withUIProperty(UIPropertyId uiPropertyId, UIProperty<? extends UIPropertyData> propertyToModify) {
		if (propertyToModify.isFullyUndefined()) return this;
		properties.put(uiPropertyId, propertyToModify);
		return this;
	}

	public UIPropertiesBuilder withPadding(UIProperty<Padding> padding) {
		return withUIProperty(PADDING, padding);
	}

	public UIPropertiesBuilder withMargin(UIProperty<Padding> padding) {
		return withUIProperty(MARGIN, padding);
	}

	public UIPropertiesBuilder withShape(UIProperty<PolygonPointsIterator> polygonPointsIterator) {
		return withUIProperty(SHAPE, polygonPointsIterator);
	}

	public UIPropertiesBuilder withMeasurement(UIProperty<Measurement> measurement) {
		return withUIProperty(MEASUREMENT, measurement);
	}

	public UIPropertiesBuilder withBorder(UIProperty<Border> border) {
		return withUIProperty(BORDER, border);
	}

	public UIPropertiesBuilder withBackgroundColor(UIProperty<Color> color) {
		return withUIProperty(BACKGROUND_COLOR, color);
	}

	public UIPropertiesBuilder withUIProperties(UIProperties uiProperties) {
		for (Map.Entry<UIPropertyId, UIProperty<? extends UIPropertyData>> uiProperty : uiProperties) {
			withUIProperty(uiProperty.getKey(), uiProperty.getValue());
		}
		return this;
	}

	public UIProperties build() {
		return new UIProperties(properties);
	}

	public UIProperties applyStyles(Set<UIProperties> uiPropertiesToApply) {
		for (UIProperties uiPropertyToApply : uiPropertiesToApply) {
			withUIProperties(uiPropertyToApply);
		}
		return build();
	}
}
