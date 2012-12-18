package com.mgs.fantasi.views;

import com.mgs.fantasi.measurements.Measurement;
import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.ui.wireframe.Margin;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public abstract class BaseView<T extends BaseView> implements View {
	private final UIProperties uiProperties = new UIProperties();

	public final BaseView withShape (PolygonPointsIterator shape){
		uiProperties.setShape(shape);
		return this;
	}

	public T withMargin(Margin margin) {
		uiProperties.setMargin(margin);
		return (T) this;
	}

	public T withName(String name) {
		uiProperties.setName(name);
		return (T) this;
	}

	public T withMeasurement(Measurement measurement) {
		uiProperties.setMeasurement(measurement);
		return (T) this;
	}

	public void setShape(PolygonPointsIterator shape) {
		uiProperties.setShape(shape);
	}

	public void setMargin(Margin margin) {
		uiProperties.setMargin(margin);
	}

	public void setName(String name) {
		uiProperties.setName(name);
	}

	public void setMeasurement(Measurement measurement) {
		uiProperties.setMeasurement(measurement);
	}

	public static class NativeRectanguarShape implements PolygonPointsIterator {
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
		copy.setShape(uiProperties.getShape());
		copy.setMargin(uiProperties.getMargin());
		copy.setName(uiProperties.getName());
		copy.setMeasurement(uiProperties.getMeasurement());
		return copy;
	}

	protected abstract T copy();

	@Override
	public Margin getMargin() {
		return uiProperties.getMargin();
	}

	@Override
	public String getName() {
		return uiProperties.getName();
	}

	@Override
	public PolygonPointsIterator getShape() {
		return uiProperties.getShape();
	}

	public Measurement getMeasurement() {
		return uiProperties.getMeasurement();
	}
}
