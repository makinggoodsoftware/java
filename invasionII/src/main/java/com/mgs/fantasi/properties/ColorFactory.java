package com.mgs.fantasi.properties;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import static com.mgs.fantasi.properties.NullUIProperty.nullProperty;

public abstract class ColorFactory {
	private static final NullUIProperty<Color> COLOR_NULL_PROPERTY = nullProperty(Color.class);
	private static final NotNullUIProperty<Color> COLOR_TRANSPARENT_PROPERTY = new NotNullUIProperty<Color>(new TransparentColor());

	public static UIPropertyProvider<Color> newColorFromAwt(java.awt.Color color) {
        return new NotNullUIProperty<Color>(new Color(color));
	}

    @SuppressWarnings("unused")
    public static UIPropertyProvider<Color> nullColor() {
		return COLOR_NULL_PROPERTY;
	}

	public static UIPropertyProvider<Color> transparent() {
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
            return EqualsBuilder.reflectionEquals(this, o);        }

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
