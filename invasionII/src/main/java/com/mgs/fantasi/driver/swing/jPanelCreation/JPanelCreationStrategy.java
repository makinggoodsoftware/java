package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.UIProperties;

import javax.swing.*;

public interface JPanelCreationStrategy {
    JPanel create(UIProperties uiProperties);
}
