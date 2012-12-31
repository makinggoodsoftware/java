package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.UIProperties;

public class JPanelCreationStrategyFactory {
    public JPanelCreationStrategy getJPanelCreationStrategy(UIProperties uiProperties) {
        return uiProperties.getShape().isRectangular() ?
                new StandardJPanelCreationStrategy() :
                new NonRectangularJPanelCreationStrategy(uiProperties.getShape());
    }
}
