package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.UIProperties;

import javax.swing.*;

public class NonRectangularJPanelCreationStrategy implements JPanelCreationStrategy{
    private final UIProperties uiProperties;

    public NonRectangularJPanelCreationStrategy(UIProperties uiProperties) {
        this.uiProperties = uiProperties;
    }

    @Override
    public JPanel create() {
        return new JPanelWithDifferentShape(uiProperties.getShape(), uiProperties);
    }
}
