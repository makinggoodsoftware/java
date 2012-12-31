package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.properties.UIProperties;

import javax.swing.*;

public interface JPanelCreationStrategy {
    JPanel run(UIProperties uiProperties);
}
