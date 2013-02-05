package com.mgs.fantasi.properties;

import com.mgs.fantasi.properties.data.Border;
import com.mgs.fantasi.properties.data.Color;
import com.mgs.fantasi.properties.data.Padding;
import com.mgs.fantasi.properties.data.UIPropertyData;
import com.mgs.fantasi.properties.data.measurements.Measurement;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;

import java.util.Iterator;
import java.util.Map;

public class UIProperties implements Iterable<Map.Entry<UIPropertyId, UIProperty<? extends UIPropertyData>>>, UIPropertiesReader {
	private final Map<UIPropertyId, UIProperty<? extends UIPropertyData>> properties;

	UIProperties(Map<UIPropertyId, UIProperty<? extends UIPropertyData>> properties) {
		this.properties = properties;
	}

	public UIProperty<Color> getBackgroundColor() {
		return (UIProperty<Color>) properties.get(UIPropertyId.BACKGROUND_COLOR);
	}

	@Override
	public UIProperty<Color> getForegroundColor() {
		return (UIProperty<Color>) properties.get(UIPropertyId.FOREGROUND_COLOR);
	}

	public UIProperty<Border> getBorder() {
		return (UIProperty<Border>) properties.get(UIPropertyId.BORDER);
	}

	public UIProperty<Padding> getPadding() {
		return (UIProperty<Padding>) properties.get(UIPropertyId.PADDING);
	}

	public UIProperty<PolygonPointsIterator> getShape() {
		return (UIProperty<PolygonPointsIterator>) properties.get(UIPropertyId.SHAPE);
	}

	public UIProperty<Measurement> getMeasurement() {
		return (UIProperty<Measurement>) properties.get(UIPropertyId.MEASUREMENT);
	}

	@Override
	public Iterator<Map.Entry<UIPropertyId, UIProperty<? extends UIPropertyData>>> iterator() {
		return properties.entrySet().iterator();
	}

}