package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.UIProperties;

public class JPanelCreationStrategyFactory {
    public JPanelCreationStrategy forUIProperties(UIProperties uiProperties) {
        JPanelCreationStrategy jPanelCreationStrategy = uiProperties.getShape().isRectangular() ?
                new StandardJPanelCreationStrategy(uiProperties) :
                new NonRectangularJPanelCreationStrategy(uiProperties);


        return  uiProperties.getPadding().isEmpty() ?
                    jPanelCreationStrategy:
                    new DecoratedJPanelWithPadding(jPanelCreationStrategy, uiProperties.getPadding());
    }
}
