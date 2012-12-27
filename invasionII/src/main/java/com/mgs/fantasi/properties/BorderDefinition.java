package com.mgs.fantasi.properties;

import com.mgs.fantasi.profile.NotNullProperty;
import com.mgs.fantasi.profile.PropertyDefinition;
import com.mgs.fantasi.profile.UIProperty;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import static com.mgs.fantasi.profile.NullProperty.nullProperty;

public abstract class BorderDefinition {
    public static PropertyDefinition<BorderUI> zero(){
        return new NotNullProperty<BorderUI>(new BorderUI(nullProperty(ColorFactory.Color.class), 0));
	}

	public static PropertyDefinition<BorderUI> newBorder(PropertyDefinition<ColorFactory.Color> color, int width) {
        return new NotNullProperty<BorderUI>(new BorderUI(color, width));
	}

    public static class BorderUI implements UIProperty {
        private final PropertyDefinition<ColorFactory.Color> color;
        private final int width;

        public BorderUI(PropertyDefinition<ColorFactory.Color> color, int width) {
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
			if (!(o instanceof BorderUI)) return false;

			BorderUI borderUI = (BorderUI) o;

			if (width != borderUI.width) return false;
			if (color != null ? !color.equals(borderUI.color) : borderUI.color != null) return false;

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
