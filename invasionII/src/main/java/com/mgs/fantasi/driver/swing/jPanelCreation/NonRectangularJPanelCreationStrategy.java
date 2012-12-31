package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;

import javax.swing.*;

public class NonRectangularJPanelCreationStrategy implements JPanelCreationStrategy{
    private final PolygonPointsIterator shape;

    public NonRectangularJPanelCreationStrategy(PolygonPointsIterator shape) {
        this.shape = shape;
    }

    @Override
    public JPanel create(UIProperties uiProperties) {
        return new JPanelWithDifferentShape(uiProperties.getShape(), uiProperties);
    }
}
