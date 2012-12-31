package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.UIProperties;

public class JPanelCreationStrategyFactory {
    public JPanelCreationStrategy forUIProperties(UIProperties uiProperties) {
        JPanelCreationStrategy jPanelCreationStrategy = uiProperties.getShape().isRectangular() ?
                new StandardJPanelCreationStrategy() :
                new NonRectangularJPanelCreationStrategy(uiProperties.getShape());


        return  uiProperties.getPadding().isEmpty() ?
                    jPanelCreationStrategy:
                    new DecoratedJPanelWithPadding(jPanelCreationStrategy);
    }
}
