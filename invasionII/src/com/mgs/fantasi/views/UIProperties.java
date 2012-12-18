package com.mgs.fantasi.views;

import com.mgs.fantasi.measurements.Measurement;
import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.ui.wireframe.Margin;

public class UIProperties {
	PolygonPointsIterator shape = new BaseView.NativeRectanguarShape();
	Margin margin = Margin.noMargin();
	String name = "";
	Measurement measurement;

	public UIProperties() {
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
}