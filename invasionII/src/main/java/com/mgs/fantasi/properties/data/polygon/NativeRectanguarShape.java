package com.mgs.fantasi.properties.data.polygon;

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
		throw new RuntimeException("Can't return list of points for native dimensions");
	}

}
