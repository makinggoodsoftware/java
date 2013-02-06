package com.mgs.fantasi.properties;

import com.mgs.fantasi.properties.data.Border;
import com.mgs.fantasi.properties.data.Color;
import com.mgs.fantasi.properties.data.UIPropertyData;

public abstract class UIPropertyFactory {
	public static UIProperty<Border> newBorder(java.awt.Color color, int width) {
		return new FullyDefinedUIProperty<Border>(new Border(width, foregroundColorFromAwt(color)), UIPropertyType.BORDER);
	}

	public static UIProperty<Color> foregroundColorFromAwt(java.awt.Color color) {
		return new FullyDefinedUIProperty<Color>(new Color(color), UIPropertyType.FOREGROUND_COLOR);
	}

	public static UIProperty<Color> backgroundColorFromAwt(java.awt.Color color) {
		return new FullyDefinedUIProperty<Color>(new Color(color), UIPropertyType.BACKGROUND_COLOR);
	}


	public static <Z extends UIPropertyData> UIProperty<Z> uiProperty(Z data, UIPropertyType type) {
		return new FullyDefinedUIProperty<Z>(data, type);
	}

	public static <Z extends UIPropertyData> UIProperty<Z> empty(UIPropertyType type) {
		return EmptyOrUndefinedUIProperty.empty(type);
	}

	public static <Z extends UIPropertyData> UIProperty<Z> undefined(UIPropertyType type) {
		return EmptyOrUndefinedUIProperty.undefined(type);
	}

	private static class EmptyOrUndefinedUIProperty<Z extends UIPropertyData> implements UIProperty<Z> {
		private final boolean isEmpty;

		public static <Z extends UIPropertyData> EmptyOrUndefinedUIProperty<Z> empty(UIPropertyType type) {
			return new EmptyOrUndefinedUIProperty<Z>(true);
		}

		public static <Z extends UIPropertyData> EmptyOrUndefinedUIProperty<Z> undefined(UIPropertyType type) {
			return new EmptyOrUndefinedUIProperty<Z>(false);
		}

		private EmptyOrUndefinedUIProperty(boolean isEmpty) {
			this.isEmpty = isEmpty;
		}

		@Override
		public boolean isEmpty() {
			return isEmpty;
		}

		@Override
		public boolean isDefined() {
			return !isEmpty();
		}

		@Override
		public boolean isFullyDefined() {
			return false;
		}

		@Override
		public boolean isFullyUndefined() {
			return !isEmpty();
		}

		@Override
		public Z getValue() {
			throw new RuntimeException("Can't get the value for an undefined or empty property");
		}
	}
}
