package com.mgs.fantasi.properties.data.polygon;

import com.mgs.fantasi.properties.data.UIPropertyData;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public interface PolygonPointsIterator extends UIPropertyData {
	boolean isRectangular();

	List<Point2D.Double> getPointListFromBottomLeftCorner(Dimension size);

	PolygonPointsIterator copy();
}
