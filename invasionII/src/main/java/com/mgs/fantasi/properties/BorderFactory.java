package com.mgs.fantasi.properties;

import com.mgs.fantasi.profile.NotNullProperty;
import com.mgs.fantasi.profile.NullProperty;
import com.mgs.fantasi.profile.PropertyDefinition;
import com.mgs.fantasi.profile.UIProperty;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import static com.mgs.fantasi.profile.NullProperty.nullProperty;
import static com.mgs.fantasi.properties.ColorFactory.transparent;

public abstract class BorderFactory {
	public static final NullProperty<Border> BORDER_NULL_PROPERTY = nullProperty(Border.class);
	public static final PropertyDefinition<Border> BORDER_EMPTY_PROPERTY = newBorder(transparent(), 0);

	public static PropertyDefinition<Border> nullBorder(){
        return BORDER_NULL_PROPERTY;
	}

	public static PropertyDefinition<Border> noBorder(){
		return BORDER_EMPTY_PROPERTY;
	}

	public static PropertyDefinition<Border> newBorder(PropertyDefinition<ColorFactory.Color> color, int width) {
		if (! color.isDefined()) throw new RuntimeException("Can't define a border without color");
        return new NotNullProperty<Border>(new Border(color, width));
	}

    public static class Border implements UIProperty {
        private final PropertyDefinition<ColorFactory.Color> color;
        private final int width;

        public Border(PropertyDefinition<ColorFactory.Color> color, int width) {
            if (!color.isDefined() && width!=0) throw new RuntimeException("Invalid combination!!");
            this.color = color;
            this.width = width;
        }

        public PropertyDefinition<ColorFactory.Color> getColor() {
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

			if (width != border.width) return false;
			if (color != null ? !color.equals(border.color) : border.color != null) return false;

			return true;
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
