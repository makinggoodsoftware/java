package com.mgs.fantasi.properties.polygon;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public class NativeRectanguarShape implements PolygonPointsIterator {
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
