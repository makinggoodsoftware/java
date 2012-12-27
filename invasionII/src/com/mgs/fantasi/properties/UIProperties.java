package com.mgs.fantasi.properties;

import com.mgs.fantasi.profile.UIStyle;
import com.mgs.fantasi.properties.measurements.Measurement;
import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;
import com.mgs.fantasi.rendering.Padding;
import com.mgs.fantasi.views.BaseView;

import java.util.Set;

import static com.mgs.fantasi.properties.measurements.EmptyMeasurement.emptyMeasurement;

public class UIProperties {
	public static final String EMPTY_NAME = "";

	PolygonPointsIterator shape = new BaseView.NativeRectanguarShape();
	Padding padding = Padding.zero();
	String name = EMPTY_NAME;
	Measurement measurement = emptyMeasurement();
	private BorderDefinition border = BorderDefinition.zero();
	private ColorDefinition backgroundColor = ColorDefinition.transparent();

	public UIProperties() {
	}

	public void applyStyle(UIStyle uiStyle){
		border = uiStyle.getBorderProperty().merge(border);
		backgroundColor = uiStyle.getBackgroundColorProperty().merge(backgroundColor);
	}

	public void setBorder(BorderDefinition border) {
		this.border = border;
	}

	public void setShape(PolygonPointsIterator shape) {
		this.shape = shape;
	}

	public void setPadding(Padding padding) {
		this.padding = padding;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMeasurement(Measurement measurement) {
		this.measurement = measurement;
	}

	public Padding getPadding() {
		return padding;
	}

	public String getName() {
		return name;
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
		copy.setName(name);
		copy.setShape(shape.copy());
		return copy;
	}

	public void copyFrom(UIProperties that) {
		UIProperties copyOfThat = that.copy();
		this.shape = copyOfThat.shape;
		this.padding = copyOfThat.padding;
		this.name = copyOfThat.name;
		this.measurement = copyOfThat.measurement;
	}

	public void setBackgroundColor(ColorDefinition backgroundColor) {
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

	public ColorDefinition getBackgroundColor() {
		return backgroundColor;
	}

	public BorderDefinition getBorder() {
		return border;
	}
}