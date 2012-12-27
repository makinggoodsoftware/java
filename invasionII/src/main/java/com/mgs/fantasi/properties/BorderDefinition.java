package com.mgs.fantasi.properties;

import com.mgs.fantasi.profile.*;
import org.apache.commons.beanutils.BeanUtils;

public abstract class BorderDefinition {
    public static PropertyDefinition<BorderDefinitionBean> zero(){
        return new NotNullProperty<BorderDefinitionBean>(new BorderDefinitionBean(new NullProperty<ColorDefinition.ColorDefinitionBean>(), 0));
	}

	public static PropertyDefinition<BorderDefinitionBean> newBorder(PropertyDefinition<ColorDefinition.ColorDefinitionBean> color, int width) {
        return new NotNullProperty<BorderDefinitionBean>(new BorderDefinitionBean(color, width));
	}

    public static class BorderDefinitionBean implements PropertyDefinitionBean {
        private final PropertyDefinition<ColorDefinition.ColorDefinitionBean> color;
        private final int width;

        public BorderDefinitionBean(PropertyDefinition<ColorDefinition.ColorDefinitionBean> color, int width) {
            if (!color.isDefined() && width!=0) throw new RuntimeException("Invalid combination!!");
            this.color = color;
            this.width = width;
        }

        public PropertyDefinition<ColorDefinition.ColorDefinitionBean> getColor() {
            return color;
        }

        public int getWidth() {
            return width;
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
            return color.isDefined();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            BorderDefinitionBean that = (BorderDefinitionBean) o;

            if (width != that.width) return false;
            if (color != null ? !color.equals(that.color) : that.color != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = color != null ? color.hashCode() : 0;
            result = 31 * result + width;
            return result;
        }
    }
}
