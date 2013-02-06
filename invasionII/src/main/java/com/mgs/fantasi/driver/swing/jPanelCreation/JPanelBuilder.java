package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.WireframeContentType;

import javax.swing.*;

public interface JPanelBuilder {
	JPanel build(WireframeContentType contentType);

	JPanelBuilder withChild(JPanel child, CollocationInfo collocationInfo);
}
