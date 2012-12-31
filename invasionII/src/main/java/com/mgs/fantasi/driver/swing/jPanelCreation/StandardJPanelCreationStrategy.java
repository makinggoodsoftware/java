package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.driver.swing.SwingUINativeRenderer;
import com.mgs.fantasi.properties.UIProperties;

import javax.swing.*;

public class StandardJPanelCreationStrategy implements JPanelCreationStrategy{
    @Override
    public JPanel create(UIProperties uiProperties) {
        JPanel jPanel1 = new JPanel();
        jPanel1.setOpaque(false);
        JPanel jPanel = jPanel1;
        SwingUINativeRenderer.applyUIProperties(jPanel, uiProperties);
        return jPanel;
    }
}
