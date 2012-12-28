package com.mgs.fantasi.properties;

import com.mgs.fantasi.profile.PropertyDefinition;
import com.mgs.fantasi.profile.UIStyle;
import com.mgs.fantasi.properties.measurements.Measurement;
import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;
import com.mgs.fantasi.rendering.Padding;
import com.mgs.fantasi.views.BaseView;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Set;

import static com.mgs.fantasi.properties.BorderFactory.noBorder;
import static com.mgs.fantasi.properties.ColorFactory.transparent;
import static com.mgs.fantasi.properties.measurements.EmptyMeasurement.emptyMeasurement;

public class UIProperties {

    PolygonPointsIterator shape = new BaseView.NativeRectanguarShape();
	Padding padding = Padding.zero();
	Measurement measurement = emptyMeasurement();
	private PropertyDefinition<BorderFactory.Border> border = noBorder();
	private PropertyDefinition<ColorFactory.Color> backgroundColor = transparent();

	public UIProperties() {
	}

	public void applyStyle(UIStyle uiStyle){
        border = uiStyle.getBorderProperty().merge(border);
		backgroundColor = uiStyle.getBackgroundColorProperty().merge(backgroundColor);
	}

	public void setBorder(PropertyDefinition<BorderFactory.Border> border) {
		this.border = border;
	}

	public void setShape(PolygonPointsIterator shape) {
		this.shape = shape;
	}

	public void setPadding(Padding padding) {
		this.padding = padding;
	}

	public void setMeasurement(Measurement measurement) {
		this.measurement = measurement;
	}

	public Padding getPadding() {
		return padding;
	}

	public PolygonPointsIterator getShape() {
		return shape;
	}

	public Measurement getMeasurement() {
		return measurement;
	}

	public UIProperties copy() {
        try {
            return (UIProperties) BeanUtils.cloneBean(this);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected exception copying UIProperties", e);
        }
    }

	public void setBackgroundColor(PropertyDefinition<ColorFactory.Color> backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public UIProperties withStyles (Set<UIStyle> styles){
		applyStyles(styles);
		return this;
	}

	public void applyStyles(Set<UIStyle> styles) {
		for (UIStyle style : styles) {
			applyStyle(style);
		}
	}

	public PropertyDefinition<ColorFactory.Color> getBackgroundColor() {
		return backgroundColor;
	}

	public PropertyDefinition<BorderFactory.Border> getBorder() {
		return border;
	}
}