package com.mgs.fantasi.properties;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import static com.mgs.fantasi.properties.ColorFactory.transparent;
import static com.mgs.fantasi.properties.NullUIProperty.nullProperty;

public abstract class BorderFactory {
	public static final NullUIProperty<Border> BORDER_NULL_PROPERTY = nullProperty(Border.class);
	public static final UIPropertyProvider<Border> BORDER_EMPTY_UI_PROPERTY = newBorder(transparent(), 0);

    @SuppressWarnings("unused")
    public static UIPropertyProvider<Border> nullBorder(){
        return BORDER_NULL_PROPERTY;
	}

	public static UIPropertyProvider<Border> noBorder(){
		return BORDER_EMPTY_UI_PROPERTY;
	}

	public static UIPropertyProvider<Border> newBorder(UIPropertyProvider<ColorFactory.Color> color, int width) {
		if (! color.isDefined()) throw new RuntimeException("Can't define a border without color");
        return new NotNullUIProperty<Border>(new Border(color, width));
	}

    public static class Border implements UIProperty {
        private final UIPropertyProvider<ColorFactory.Color> color;
        private final int width;

        public Border(UIPropertyProvider<ColorFactory.Color> color, int width) {
            if (!color.isDefined() && width!=0) throw new RuntimeException("Invalid combination!!");
            this.color = color;
            this.width = width;
        }

        public UIPropertyProvider<ColorFactory.Color> getColor() {
            return color;
        }

        public int getWidth() {
            return width;
        }

        @Override
        public UIProperty copy() {
            try {
                return (UIProperty) BeanUtils.cloneBean(this);
            } catch (Exception e) {
                throw new RuntimeException("Unhandled exception", e);
            }
        }

        @Override
        public boolean isFullyDefined() {
            return color.isDefined();
        }

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof Border)) return false;

			Border border = (Border) o;

            return width == border.width && !(color != null ? !color.equals(border.color) : border.color != null);

        }

		@Override
		public int hashCode() {
			int result = color != null ? color.hashCode() : 0;
			result = 31 * result + width;
			return result;
		}

		@Override
		public String toString() {
			return new ToStringBuilder(this).
					append("color", color).
					append("width", width).
					toString();
		}
	}
}
