package com.mgs.fantasi.properties;

import com.mgs.fantasi.profile.*;
import org.apache.commons.beanutils.BeanUtils;

import java.awt.*;

public abstract class ColorDefinition {
	public static PropertyDefinition<ColorDefinitionBean> newColor(Color color) {
        return new NotNullProperty<ColorDefinitionBean>(new ColorDefinitionBean(color));
	}

    public static PropertyDefinition<ColorDefinitionBean> newTransparent() {
		return new NullProperty<ColorDefinitionBean>();
	}
	
    public static class ColorDefinitionBean implements PropertyDefinitionBean {
        private final Color color;

        public ColorDefinitionBean(Color color) {
            if (color == null) throw new RuntimeException("We don't take nulls!");
            this.color = color;
        }

        @Override
        public PropertyDefinitionBean copy() {
            try {
                return (PropertyDefinitionBean) BeanUtils.cloneBean(this);
            } catch (Exception e) {
                throw new RuntimeException("Unhandled exception", e);
            }
        }

        @Override
        public boolean isDefined() {
            return true;
        }

        public Color getColor() {
            return color;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ColorDefinitionBean that = (ColorDefinitionBean) o;

            if (color != null ? !color.equals(that.color) : that.color != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return color != null ? color.hashCode() : 0;
        }
    }
}
