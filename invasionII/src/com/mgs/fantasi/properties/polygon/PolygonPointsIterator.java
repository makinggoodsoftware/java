package com.mgs.fantasi.properties.polygon;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public interface PolygonPointsIterator {
	boolean isRectangular();

	List<Point2D.Double> getPointListFromBottomLeftCorner(Dimension size);

	PolygonPointsIterator copy();
}
