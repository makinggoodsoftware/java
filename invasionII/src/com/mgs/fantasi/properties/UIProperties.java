package com.mgs.fantasi.properties;

import com.mgs.fantasi.profile.UIStyle;
import com.mgs.fantasi.properties.measurements.Measurement;
import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;
import com.mgs.fantasi.rendering.Margin;
import com.mgs.fantasi.views.BaseView;

import java.awt.*;
import java.util.Set;

public class UIProperties {
	PolygonPointsIterator shape = new BaseView.NativeRectanguarShape();
	Margin margin = Margin.noMargin();
	String name = "";
	Measurement measurement;
	private BorderDefinition border;
	private Color backgroundColor;

	public UIProperties() {
	}

	public void applyStyle(UIStyle uiStyle){
		setBorder(uiStyle.getBorder());
		setBackgroundColor(uiStyle.getBackgroundColor());
	}

	private void setBorder(BorderDefinition border) {
		this.border = border;
	}

	public void setShape(PolygonPointsIterator shape) {
		this.shape = shape;
	}

	public void setMargin(Margin margin) {
		this.margin = margin;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMeasurement(Measurement measurement) {
		this.measurement = measurement;
	}

	public Margin getMargin() {
		return margin;
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
		copy.setMargin(getMargin().copy());
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
		this.margin = copyOfThat.margin;
		this.name = copyOfThat.name;
		this.measurement = copyOfThat.measurement;
	}

	public void setBackgroundColor(Color backgroundColor) {
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

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public BorderDefinition getBorder() {
		return border;
	}
}