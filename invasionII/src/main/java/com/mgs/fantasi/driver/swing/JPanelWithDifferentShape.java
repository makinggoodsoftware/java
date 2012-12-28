package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.profile.PropertyDefinition;
import com.mgs.fantasi.properties.*;
import com.mgs.fantasi.properties.BorderFactory;
import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;

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
		PropertyDefinition<BorderFactory.Border> border = uiProperties.getBorder();
		float borderThickness = 0;
		PropertyDefinition<ColorFactory.Color> foregroundColor = ColorFactory.newColorFromAwt(g2d.getColor());
		if (border.isDefined()){
			borderThickness = border.getData().getWidth();
            PropertyDefinition<ColorFactory.Color> color = border.getData().getColor();
            if (color.isDefined()){
                foregroundColor = color;
            }
		}
        PropertyDefinition<ColorFactory.Color> backgroundColor = uiProperties.getBackgroundColor();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		drawHexagon(g2d, getSize(), foregroundColor, backgroundColor, borderThickness * 2);
	}

	private void drawHexagon(Graphics2D g2d, Dimension size, PropertyDefinition<ColorFactory.Color> foregroundColor, PropertyDefinition<ColorFactory.Color> backgroundColor, float thickness) {
		int insetSpace = (int) thickness;
		Dimension sizeWithoutBorders = new Dimension(size.width - insetSpace, size.height - insetSpace);
		if (sizeWithoutBorders.getWidth()<0 || sizeWithoutBorders.getHeight()<0) return;
		java.util.List<Point2D.Double> hexagonPoints = shape.getPointListFromBottomLeftCorner(sizeWithoutBorders);
		Path2D.Double path = new Path2D.Double();
		nextLine(path, hexagonPoints.get(0), thickness);
		for (int i = 1; i < hexagonPoints.size() ; i++) {
			nextLine(path, hexagonPoints.get(i), thickness);
		}
		path.closePath();
        if (foregroundColor.isDefined()){
		    g2d.setColor(foregroundColor.getData().getColorAsAwt());
        }
		g2d.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g2d.draw(path);
        if (backgroundColor.isDefined()){
		    g2d.setColor(backgroundColor.getData().getColorAsAwt());
        }
		g2d.fill(path);
	}

	private void nextLine(Path2D.Double path, Point2D.Double point, float thickness) {
		double x = point.getX();
		double y = point.getY();

		x+=thickness/2;
		y+=thickness/2;

		if (path.getCurrentPoint() == null){
			path.moveTo(x, y);
		}else{
			path.lineTo(x, y);
		}
	}
}
