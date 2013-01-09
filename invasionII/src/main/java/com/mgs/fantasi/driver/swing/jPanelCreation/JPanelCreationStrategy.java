package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.wireframe.WireframeType;

import javax.swing.*;

public interface JPanelCreationStrategy {
	JPanel create();

	WireframeType getType();
}
