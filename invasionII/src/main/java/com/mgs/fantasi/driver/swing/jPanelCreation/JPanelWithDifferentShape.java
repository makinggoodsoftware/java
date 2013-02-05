package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.properties.UIProperty;
import com.mgs.fantasi.properties.data.Border;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class JPanelWithDifferentShape extends JPanel {
	private final PolygonPointsIterator shape;
	private final UIProperties uiProperties;

	public JPanelWithDifferentShape(PolygonPointsIterator shape, UIProperties uiProperties) {
		this.shape = shape;
		this.uiProperties = uiProperties;
		setOpaque(false);
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		Graphics2D g2d = (Graphics2D) graphics;
		float borderThickness;
		Color borderColor = g2d.getColor();
		UIProperty<Border> border = uiProperties.getBorder();
		if (border.isEmpty()) {
			borderThickness = 0;
		} else {
			borderThickness = border.getValue().getWidth();
			borderColor = border.getValue().getColor().getValue().getColorAsAwt();
		}
		UIProperty<com.mgs.fantasi.properties.data.Color> backgroundColor = uiProperties.getBackgroundColor();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		drawHexagon(g2d, getSize(), borderColor, backgroundColor, borderThickness * 2);
	}

	private void drawHexagon(Graphics2D g2d, Dimension size, Color borderColor, UIProperty<com.mgs.fantasi.properties.data.Color> backgroundColor, float thickness) {
		int insetSpace = (int) thickness;
		Dimension sizeWithoutBorders = new Dimension(size.width - insetSpace, size.height - insetSpace);
		if (sizeWithoutBorders.getWidth() < 0 || sizeWithoutBorders.getHeight() < 0) return;
		java.util.List<Point2D.Double> hexagonPoints = shape.getPointListFromBottomLeftCorner(sizeWithoutBorders);
		Path2D.Double path = new Path2D.Double();
		nextLine(path, hexagonPoints.get(0), thickness);
		for (int i = 1; i < hexagonPoints.size(); i++) {
			nextLine(path, hexagonPoints.get(i), thickness);
		}
		path.closePath();
		g2d.setColor(borderColor);
		g2d.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g2d.draw(path);
		if (backgroundColor.isFullyDefined()) {
			g2d.setColor(backgroundColor.getValue().getColorAsAwt());
		}
		g2d.fill(path);
	}

	private void nextLine(Path2D.Double path, Point2D.Double point, float thickness) {
		double x = point.getX();
		double y = point.getY();

		x += thickness / 2;
		y += thickness / 2;

		if (path.getCurrentPoint() == null) {
			path.moveTo(x, y);
		} else {
			path.lineTo(x, y);
		}
	}
}