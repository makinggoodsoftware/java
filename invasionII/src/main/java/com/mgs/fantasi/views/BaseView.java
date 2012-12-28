package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.Padding;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.properties.measurements.Measurement;
import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public abstract class BaseView<T extends BaseView> implements View {
	private final UIProperties uiProperties = new UIProperties();
    private String name = "";

    @SuppressWarnings(value = "unchecked")
    public T withPadding(Padding padding) {
		getUiProperties().setPadding(padding);
		return (T) this;
	}

    @SuppressWarnings(value = "unchecked")
	public T withName(String name) {
		this.name = name;
		return (T) this;
	}

    @SuppressWarnings(value = "unchecked")
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
	public UIProperties takeUiPropertiesSnapshot() {
		return getUiProperties().copy();
	}

    @Override
    public String getName() {
        return name;
    }
}
