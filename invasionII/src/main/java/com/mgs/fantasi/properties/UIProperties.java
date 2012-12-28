package com.mgs.fantasi.properties;

import com.mgs.fantasi.profile.PropertyDefinition;
import com.mgs.fantasi.profile.UIStyle;
import com.mgs.fantasi.properties.measurements.Measurement;
import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;
import com.mgs.fantasi.rendering.Padding;
import com.mgs.fantasi.views.BaseView;

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
		UIProperties copy = new UIProperties();
		copy.setPadding(getPadding().copy());
		if (getMeasurement()!=null){
			copy.setMeasurement(getMeasurement().copy());
		}
		copy.setShape(shape.copy());
		return copy;
	}

	public void copyFrom(UIProperties that) {
		UIProperties copyOfThat = that.copy();
		this.shape = copyOfThat.shape;
		this.padding = copyOfThat.padding;
		this.measurement = copyOfThat.measurement;
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