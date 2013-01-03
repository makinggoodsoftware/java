package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.driver.swing.SwingUtils;
import com.mgs.fantasi.properties.UIProperties;

import javax.swing.*;

public class StandardJPanelCreationStrategy implements JPanelCreationStrategy{
    private final UIProperties uiProperties;

    public StandardJPanelCreationStrategy(UIProperties uiProperties) {
        this.uiProperties = uiProperties;
    }

    @Override
    public JPanel create() {
        JPanel jPanel = new JPanel();
        jPanel.setOpaque(false);
        SwingUtils.applyUIProperties(jPanel, uiProperties);
        return jPanel;
    }
}
