package com.mgs.fantasi.properties;

import com.mgs.fantasi.properties.measurements.Measurement;
import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;

public class UIPropertiesBuilder {
	private UIProperty<Border> border;
	private UIProperty<Color> backgroundColor;
	private UIProperty<Color> foregroundColor;
	private UIProperty<Padding> padding;
	private UIProperty<PolygonPointsIterator> shape;
	private UIProperty<Measurement> measurement;

	public UIPropertiesBuilder(UIPropertiesReader initializationStrategy) {
		border = initializationStrategy.getBorder();
		backgroundColor = initializationStrategy.getBackgroundColor();
		foregroundColor = initializationStrategy.getForegroundColor();
		padding = initializationStrategy.getPadding();
		shape = initializationStrategy.getShape();
		measurement = initializationStrategy.getMeasurement();
	}

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

	public UIPropertiesBuilder withPadding(UIProperty<Padding> padding) {
		this.padding = padding;
		return this;
	}

	public UIPropertiesBuilder withShape(UIProperty<PolygonPointsIterator> polygonPointsIterator) {
		this.shape = polygonPointsIterator;
		return this;
	}

	public UIPropertiesBuilder withMeasurement(UIProperty<Measurement> measurement) {
		this.measurement = measurement;
		return this;
	}

	public UIPropertiesBuilder withBorder(UIProperty<Border> border) {
		this.border = border;
		return this;
	}

	public UIPropertiesBuilder withBackgroundColor(UIProperty<Color> color) {
		this.backgroundColor = color;
		return this;
	}

	public UIPropertiesBuilder withUIProperties(UIProperties uiProperties) {
		for (UIProperty<? extends UIPropertyData> uiProperty : uiProperties) {
			withUIProperty(uiProperty);
		}
		return this;
	}

	public UIProperties build() {
		return new UIProperties(
				border,
				backgroundColor,
				foregroundColor,
				padding,
				shape,
				measurement);
	}
}
