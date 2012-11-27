package com.mgs.fantasi.polygon;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class HexagonShape implements PolygonPointsIterator {
	@Override
	public boolean isRectangular() {
		return false;
	}

	@Override
	public List<Point2D.Double> getPointListFromBottomLeftCorner(Dimension size) {
		List<Point2D.Double> hexPoints = new ArrayList<Point2D.Double>();
		double quarterHeight = size.getHeight() / 4;
		double halfWidth = size.getWidth() / 2;

		double yBottom = 1;
		double yFirstAnchor 	= quarterHeight;
		double ySecondAnchor 	= yFirstAnchor * 3;
		double yTop 			= yFirstAnchor * 4;

		double xOrigin   = 1;
		double xCenter   = halfWidth;
		double xEndPoint = xCenter * 2;

		hexPoints.add(new Point2D.Double(xOrigin -1, 		yFirstAnchor-1));
		hexPoints.add(new Point2D.Double(xOrigin-1, 		ySecondAnchor-1));
		hexPoints.add(new Point2D.Double(xCenter-1,			yTop-1));

		hexPoints.add(new Point2D.Double(xEndPoint-1,		ySecondAnchor));
		hexPoints.add(new Point2D.Double(xEndPoint-1, 		yFirstAnchor-1));
		hexPoints.add(new Point2D.Double(xCenter-1,			yBottom-1));
		return hexPoints;
	}
}
