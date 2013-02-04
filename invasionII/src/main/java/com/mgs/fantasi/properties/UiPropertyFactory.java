package com.mgs.fantasi.properties;

public abstract class UIPropertyFactory {
	public static UIProperty<Border> newBorder(java.awt.Color color, int width) {
		return new FullyDefinedUIProperty<Border>(new Border(width, foregroundColorFromAwt(color)), PropertyType.BORDER);
	}

	public static UIProperty<Color> foregroundColorFromAwt(java.awt.Color color) {
		return new FullyDefinedUIProperty<Color>(new Color(color), PropertyType.FOREGROUND_COLOR);
	}

	public static UIProperty<Color> backgroundColorFromAwt(java.awt.Color color) {
		return new FullyDefinedUIProperty<Color>(new Color(color), PropertyType.BACKGROUND_COLOR);
	}


	public static <Z extends UIPropertyData> UIProperty<Z> uiProperty(Z data, PropertyType type) {
		return new FullyDefinedUIProperty<Z>(data, type);
	}

	public static <Z extends UIPropertyData> UIProperty<Z> empty(PropertyType type) {
		return UndefinedUIProperty.empty(type);
	}

	public static <Z extends UIPropertyData> UIProperty<Z> undefined(PropertyType type) {
		return UndefinedUIProperty.undefined(type);
	}

	private static class UndefinedUIProperty<Z extends UIPropertyData> implements UIProperty<Z> {
		private final PropertyType type;
		private final boolean isDefined;
		private final boolean isEmpty;

		public static <Z extends UIPropertyData> UndefinedUIProperty<Z> empty(PropertyType type) {
			return new UndefinedUIProperty<Z>(type, true, true);
		}

		public static <Z extends UIPropertyData> UndefinedUIProperty<Z> undefined(PropertyType type) {
			return new UndefinedUIProperty<Z>(type, false, true);
		}

		private UndefinedUIProperty(PropertyType type, boolean isDefined, boolean isEmpty) {
			this.type = type;
			this.isDefined = isDefined;
			this.isEmpty = isEmpty;
		}

		@Override
		public UIProperty<Z> copy() {
			return this;
		}

		@Override
		public boolean isEmpty() {
			return isEmpty;
		}

		@Override
		public PropertyType getType() {
			return type;
		}

		@Override
		public Z getValue() {
			throw new RuntimeException("Can't get the value for an undefined or empty property");
		}

		@Override
		public boolean isDefined() {
			return isDefined;
		}

		@Override
		public boolean isFullyDefined() {
			return false;
		}

		@Override
		public boolean isNotDefined() {
			return !isDefined();
		}
	}
}
