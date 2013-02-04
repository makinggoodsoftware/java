package com.mgs.fantasi.properties;

import com.mgs.fantasi.properties.measurements.Measurement;
import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UIProperties implements Iterable<UIProperty<? extends UIPropertyData>> {
	private final UIProperty<PolygonPointsIterator> shape;
	private final UIProperty<Padding> padding;
	private final UIProperty<Border> border;
	private final UIProperty<Color> backgroundColor;
	private final UIProperty<Color> foregroundColor;
	private final UIProperty<Measurement> measurement;

	public UIProperties(UIProperty<Border> border, UIProperty<Color> backgroundColor, UIProperty<Color> foregroundColor, UIProperty<Padding> padding, UIProperty<PolygonPointsIterator> shape, UIProperty<Measurement> measurement) {
		this.border = border;
		this.backgroundColor = backgroundColor;
		this.foregroundColor = foregroundColor;
		this.padding = padding;
		this.shape = shape;
		this.measurement = measurement;
	}

	public UIProperty<Color> getBackgroundColor() {
		return backgroundColor;
	}

	public UIProperty<Border> getBorder() {
		return border;
	}

	public UIProperty<Padding> getPadding() {
		return padding;
	}

	public UIProperty<PolygonPointsIterator> getShape() {
		return shape;
	}

	public UIProperty<Measurement> getMeasurement() {
		return measurement;
	}

	@Override
	public Iterator<UIProperty<? extends UIPropertyData>> iterator() {
		return new UIPropertyIterator(this);
	}

	private static class UIPropertyIterator implements Iterator<UIProperty<? extends UIPropertyData>> {
		private final List<UIProperty<? extends UIPropertyData>> underlyingList = new ArrayList<UIProperty<? extends UIPropertyData>>();
		private final Iterator<UIProperty<? extends UIPropertyData>> underlyingIterator;

		public UIPropertyIterator(UIProperties uiProperties) {
			underlyingList.add(uiProperties.getBackgroundColor());
			underlyingList.add(uiProperties.getBorder());
			underlyingList.add(uiProperties.getPadding());
			underlyingList.add(uiProperties.getShape());
			underlyingList.add(uiProperties.getMeasurement());
			underlyingIterator = underlyingList.iterator();
		}

		@Override
		public boolean hasNext() {
			return underlyingIterator.hasNext();
		}

		@Override
		public UIProperty<? extends UIPropertyData> next() {
			UIProperty<? extends UIPropertyData> next = underlyingIterator.next();
			if (next == null) throw new RuntimeException("Bullshit!");
			return next;
		}

		@Override
		public void remove() {
			throw new RuntimeException("Can't remove UI properties, operation not supported");
		}
	}
}