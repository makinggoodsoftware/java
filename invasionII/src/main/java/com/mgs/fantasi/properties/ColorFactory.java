package com.mgs.fantasi.properties;

import com.mgs.fantasi.profile.NotNullProperty;
import com.mgs.fantasi.profile.NullProperty;
import com.mgs.fantasi.profile.PropertyDefinition;
import com.mgs.fantasi.profile.UIProperty;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import static com.mgs.fantasi.profile.NullProperty.nullProperty;

public abstract class ColorFactory {
	private static final NullProperty<Color> COLOR_NULL_PROPERTY = nullProperty(Color.class);
	private static final NotNullProperty<Color> COLOR_TRANSPARENT_PROPERTY = new NotNullProperty<Color>(new TransparentColor());

	public static PropertyDefinition<Color> newColorFromAwt(java.awt.Color color) {
        return new NotNullProperty<Color>(new Color(color));
	}

    public static PropertyDefinition<Color> nullColor() {
		return COLOR_NULL_PROPERTY;
	}

	public static PropertyDefinition<Color> transparent() {
		return COLOR_TRANSPARENT_PROPERTY;
	}
	
    public static class Color implements UIProperty {
        private final java.awt.Color color;

        private Color(java.awt.Color color) {
            this.color = color;
        }

        @Override
        public UIProperty copy() {
            try {
                return (UIProperty) BeanUtils.cloneBean(this);
            } catch (Exception e) {
                throw new RuntimeException("Unexpected exception from apache BeanUtils!", e);
            }
        }

        @Override
        public boolean isFullyDefined() {
            return true;
        }

        public java.awt.Color getColorAsAwt() {
            return color;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Color that = (Color) o;

            if (color != null ? !color.equals(that.color) : that.color != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return color != null ? color.hashCode() : 0;
        }

		@Override
		public String toString() {
			return new ToStringBuilder(this).
					append("color", color).
					toString();
		}

		public boolean isTransparent(){
			return false;
		}
	}
	
	public static class TransparentColor extends Color{
		private TransparentColor() {
			super(null);
		}

		@Override
		public java.awt.Color getColorAsAwt() {
			throw new RuntimeException("A transparent color doesn't have an underlying awt representation, use isTransparent to find out!");
		}

		@Override
		public boolean isTransparent() {
			return true;
		}

		@Override
		public UIProperty copy() {
			return this;
		}
	}
}
