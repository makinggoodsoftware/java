package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.wireframe.WireframeContentType;

import javax.swing.*;
import java.awt.*;

public class JPanelLayoutTranslator {
	public LayoutManager translate(WireframeContentType contentType, JPanel container) {
		switch (contentType) {
			case GRID:
			case RECTANGLE:
				return new GridBagLayout();
			case LAYERS:
				return new OverlayLayout(container);
			case EMPTY:
				throw new RuntimeException("There is no layout for empty wireframes!!!");
			default:
				throw new RuntimeException("Shouldn't have reached this point in the code!!!");
		}
	}
}
