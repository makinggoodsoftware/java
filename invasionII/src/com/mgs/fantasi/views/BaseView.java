package com.mgs.fantasi.views;

import com.mgs.fantasi.measurements.Measurement;
import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.ui.wireframe.Margin;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public abstract class BaseView<T extends BaseView> implements View {
	private PolygonPointsIterator shape = new NativeRectanguarShape();
	private Margin margin = Margin.noMargin();
	private String name = "";
	private Measurement measurement;

	public final BaseView withShape (PolygonPointsIterator shape){
		this.setShape(shape);
		return this;
	}

	public T withMargin(Margin margin) {
		this.setMargin(margin);
		return (T) this;
	}

	public T withName(String name) {
		this.setName(name);
		return (T) this;
	}

	public T withMeasurement(Measurement measurement) {
		this.setMeasurement(measurement);
		return (T) this;
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

	private class NativeRectanguarShape implements PolygonPointsIterator {
		@Override
		public boolean isRectangular() {
			return true;
		}

		@Override
		public List<Point2D.Double> getPointListFromBottomLeftCorner(Dimension size) {
			throw new NotImplementedException();
		}

	}

	@Override
	public final T newCopy() {
		T copy = copy ();
		copy.setShape(getShape());
		copy.setMargin(getMargin());
		copy.setName(getName());
		copy.setMeasurement(getMeasurement());
		return copy;
	}

	protected abstract T copy();

	@Override
	public Margin getMargin() {
		return margin;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public PolygonPointsIterator getShape() {
		return shape;
	}

	public Measurement getMeasurement() {
		return measurement;
	}
}
