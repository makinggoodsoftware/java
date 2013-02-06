package com.mgs.fantasi.properties;

import com.mgs.fantasi.properties.data.Border;
import com.mgs.fantasi.properties.data.Color;
import com.mgs.fantasi.properties.data.Padding;
import com.mgs.fantasi.properties.data.UIPropertyData;
import com.mgs.fantasi.properties.data.measurements.Measurement;
import com.mgs.fantasi.properties.data.polygon.NativeRectanguarShape;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;

import java.util.HashMap;
import java.util.Map;

import static com.mgs.fantasi.properties.UIPropertyFactory.*;
import static com.mgs.fantasi.properties.UIPropertyId.*;

public class UIPropertiesBuilderFactory {
	public static UIPropertiesBuilder rectangularEmpty() {
		UIProperty<Border> border = empty(UIPropertyType.BORDER);
		UIProperty<Color> backgroundColor = empty(UIPropertyType.BACKGROUND_COLOR);
		UIProperty<Color> foregroundColor = empty(UIPropertyType.FOREGROUND_COLOR);
		UIProperty<Padding> padding = empty(UIPropertyType.PADDING);
		UIProperty<PolygonPointsIterator> shape = uiProperty((PolygonPointsIterator) new NativeRectanguarShape(), UIPropertyType.SHAPE);
		UIProperty<Measurement> measurement = empty(UIPropertyType.MEASUREMENT);

		return new UIPropertiesBuilder(newUIPropertiesMap(
				border,
				backgroundColor,
				foregroundColor,
				padding,
				shape,
				measurement
		));
	}

	public static UIPropertiesBuilder from(UIPropertiesProvider uiPropertiesProvider) {
		UIProperty<Border> border = uiPropertiesProvider.getBorder();
		UIProperty<Color> backgroundColor = uiPropertiesProvider.getBackgroundColor();
		UIProperty<Color> foregroundColor = uiPropertiesProvider.getForegroundColor();
		UIProperty<Padding> padding = uiPropertiesProvider.getPadding();
		UIProperty<PolygonPointsIterator> shape = uiPropertiesProvider.getShape();
		UIProperty<Measurement> measurement = uiPropertiesProvider.getMeasurement();

		return new UIPropertiesBuilder(newUIPropertiesMap(
				border,
				backgroundColor,
				foregroundColor,
				padding,
				shape,
				measurement
		));
	}


	public static UIPropertiesBuilder allUndefined() {
		UIProperty<Border> border = undefined(UIPropertyType.BORDER);
		UIProperty<Color> backgroundColor = undefined(UIPropertyType.BACKGROUND_COLOR);
		UIProperty<Color> foregroundColor = undefined(UIPropertyType.FOREGROUND_COLOR);
		UIProperty<Padding> padding = undefined(UIPropertyType.PADDING);
		UIProperty<PolygonPointsIterator> shape = undefined(UIPropertyType.SHAPE);
		UIProperty<Measurement> measurement = undefined(UIPropertyType.MEASUREMENT);

		return new UIPropertiesBuilder(newUIPropertiesMap(
				border,
				backgroundColor,
				foregroundColor,
				padding,
				shape,
				measurement
		));
	}

	private static Map<UIPropertyId, UIProperty<? extends UIPropertyData>> newUIPropertiesMap(
			UIProperty<Border> border,
			UIProperty<Color> backgroundColor,
			UIProperty<Color> foregroundColor,
			UIProperty<Padding> padding,
			UIProperty<PolygonPointsIterator> shape,
			UIProperty<Measurement> measurement
	) {
		Map<UIPropertyId, UIProperty<? extends UIPropertyData>> properties = new HashMap<UIPropertyId, UIProperty<? extends UIPropertyData>>();
		properties.put(BORDER, border);
		properties.put(BACKGROUND_COLOR, backgroundColor);
		properties.put(FOREGROUND_COLOR, foregroundColor);
		properties.put(PADDING, padding);
		properties.put(SHAPE, shape);
		properties.put(MEASUREMENT, measurement);
		return properties;
	}
}
