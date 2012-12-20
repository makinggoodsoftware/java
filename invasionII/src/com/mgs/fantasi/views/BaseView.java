package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.properties.measurements.Measurement;
import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;
import com.mgs.fantasi.rendering.Margin;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public abstract class BaseView<T extends BaseView> implements View {
	private final UIProperties uiProperties = new UIProperties();

	public T withPadding(Margin margin) {
		getUiProperties().setMargin(margin);
		return (T) this;
	}

	public T withName(String name) {
		getUiProperties().setName(name);
		return (T) this;
	}

	public T withMeasurement(Measurement measurement) {
		getUiProperties().setMeasurement(measurement);
		return (T) this;
	}

	@Override
	public UIProperties getUiProperties() {
		return uiProperties;
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

		@Override
		public PolygonPointsIterator copy() {
			return new NativeRectanguarShape();
		}

	}

	@Override
	public final T newCopy() {
		T copy = copySpecifics();
		copy.getUiProperties().copyFrom(getUiProperties());
		return copy;
	}

	@Override
	public UIProperties takeUiPropertiesSnapshot() {
		return getUiProperties().copy();
	}

	protected abstract T copySpecifics();

}
