package com.mgs.fantasi.properties;

import com.mgs.fantasi.properties.data.Border;
import com.mgs.fantasi.properties.data.Color;
import com.mgs.fantasi.properties.data.Padding;
import com.mgs.fantasi.properties.data.measurements.Measurement;
import com.mgs.fantasi.properties.data.polygon.NativeRectanguarShape;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;

import static com.mgs.fantasi.properties.UIPropertyFactory.*;

public class UIPropertiesBuilderFactory {
	private final static UIPropertiesReader RECTANGULAR_EMPTY = rectangularEmptyInitializer();
	private final static UIPropertiesReader ALL_UNDEFINED = allUndefinedInitializer();

	public static UIPropertiesBuilder rectangularEmpty() {
		return new UIPropertiesBuilder(RECTANGULAR_EMPTY);
	}

	public static UIPropertiesBuilder allUndefined() {
		return new UIPropertiesBuilder(ALL_UNDEFINED);
	}

	private static UIPropertiesReader rectangularEmptyInitializer() {
		return new UIPropertiesReader() {
			@Override
			public UIProperty<Border> getBorder() {
				return empty(PropertyType.BORDER);
			}

			@Override
			public UIProperty<Color> getBackgroundColor() {
				return empty(PropertyType.BACKGROUND_COLOR);
			}

			@Override
			public UIProperty<Color> getForegroundColor() {
				return empty(PropertyType.FOREGROUND_COLOR);
			}

			@Override
			public UIProperty<Padding> getPadding() {
				return empty(PropertyType.PADDING);
			}

			@Override
			public UIProperty<PolygonPointsIterator> getShape() {
				return uiProperty((PolygonPointsIterator) new NativeRectanguarShape(), PropertyType.SHAPE);
			}

			@Override
			public UIProperty<Measurement> getMeasurement() {
				return empty(PropertyType.MEASUREMENT);
			}
		};
	}

	private static UIPropertiesReader allUndefinedInitializer() {
		return new UIPropertiesReader() {
			@Override
			public UIProperty<Border> getBorder() {
				return undefined(PropertyType.BORDER);
			}

			@Override
			public UIProperty<Color> getBackgroundColor() {
				return undefined(PropertyType.BACKGROUND_COLOR);
			}

			@Override
			public UIProperty<Color> getForegroundColor() {
				return undefined(PropertyType.FOREGROUND_COLOR);
			}

			@Override
			public UIProperty<Padding> getPadding() {
				return undefined(PropertyType.PADDING);
			}

			@Override
			public UIProperty<PolygonPointsIterator> getShape() {
				return undefined(PropertyType.SHAPE);
			}

			@Override
			public UIProperty<Measurement> getMeasurement() {
				return undefined(PropertyType.MEASUREMENT);
			}
		};
	}
}
