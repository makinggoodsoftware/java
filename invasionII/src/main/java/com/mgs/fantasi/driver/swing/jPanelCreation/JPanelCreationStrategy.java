package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.wireframe.WireframeContentType;

import javax.swing.*;

public interface JPanelCreationStrategy {
	JPanel create();

	WireframeContentType getContentType();

}
