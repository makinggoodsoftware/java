package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.wireframe.WireframeContentType;

import javax.swing.*;
import java.awt.*;

public interface JPanelCreationStrategy {
	JPanel create();

	WireframeContentType getContentType();

	LayoutManager translateTypeIntoLayout(JPanel container);
}
