package com.mgs.fantasi.properties;

import com.mgs.fantasi.properties.measurements.Measurement;
import com.mgs.fantasi.properties.polygon.NativeRectanguarShape;
import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;

public class EmptyRectangularUIPropertiesBuilder implements UIPropertiesBuilder {
	private UIProperty<Border> border = UIPropertyFactory.empty(PropertyType.BORDER);
	private UIProperty<Color> backgroundColor = UIPropertyFactory.empty(PropertyType.FOREGROUND_COLOR);
	private UIProperty<Color> foregroundColor = UIPropertyFactory.empty(PropertyType.FOREGROUND_COLOR);
	private UIProperty<Padding> padding = UIPropertyFactory.empty(PropertyType.PADDING);
	private UIProperty<PolygonPointsIterator> shape = UIPropertyFactory.uiProperty((PolygonPointsIterator) new NativeRectanguarShape(), PropertyType.SHAPE);
	private UIProperty<Measurement> measurement = UIPropertyFactory.empty(PropertyType.MEASUREMENT);

	@Override
	public UIPropertiesBuilder withUIProperty(UIProperty<? extends UIPropertyData> propertyToModify) {
		if (propertyToModify.isNotDefined()) return this;

		switch (propertyToModify.getType()) {
			case SHAPE:
				this.shape = (UIProperty<PolygonPointsIterator>) propertyToModify;
				break;
			case PADDING:
				this.padding = (UIProperty<Padding>) propertyToModify;
				break;
			case BORDER:
				this.border = (UIProperty<Border>) propertyToModify;
				break;
			case BACKGROUND_COLOR:
				this.backgroundColor = (UIProperty<Color>) propertyToModify;
				break;
			case FOREGROUND_COLOR:
				this.foregroundColor = (UIProperty<Color>) propertyToModify;
				break;
			case MEASUREMENT:
				this.measurement = (UIProperty<Measurement>) propertyToModify;
				break;
			default:
				throw new RuntimeException("Unexpected line of code reached");
		}
		return this;
	}

	@Override
	public UIPropertiesBuilder withPadding(UIProperty<Padding> padding) {
		this.padding = padding;
		return this;
	}

	@Override
	public UIPropertiesBuilder withShape(UIProperty<PolygonPointsIterator> polygonPointsIterator) {
		this.shape = polygonPointsIterator;
		return this;
	}

	@Override
	public UIPropertiesBuilder withMeasurement(UIProperty<Measurement> measurement) {
		this.measurement = measurement;
		return this;
	}

	@Override
	public UIPropertiesBuilder withBorder(UIProperty<Border> border) {
		this.border = border;
		return this;
	}

	@Override
	public UIPropertiesBuilder withBackgroundColor(UIProperty<Color> color) {
		this.backgroundColor = color;
		return this;
	}

	@Override
	public UIProperties build() {
		return new UIProperties(
				border,
				backgroundColor,
				foregroundColor,
				padding,
				shape,
				measurement);
	}

	@Override
	public UIPropertiesBuilder withUIProperties(UIProperties uiProperties) {
		for (UIProperty<? extends UIPropertyData> uiProperty : uiProperties) {
			withUIProperty(uiProperty);
		}
		return this;
	}
}
