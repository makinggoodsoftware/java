package com.mgs.fantasi.polygon;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public interface PolygonPointsIterator {
	boolean isRectangular();

	List<Point2D.Double> getPointListFromBottomLeftCorner(Dimension size);
}
