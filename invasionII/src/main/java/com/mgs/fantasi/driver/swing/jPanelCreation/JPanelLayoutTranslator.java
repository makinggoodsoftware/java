package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.structure.treeAux.WireframeLayoutType;

import javax.swing.*;
import java.awt.*;

public class JPanelLayoutTranslator {
	public LayoutManager translate(WireframeLayoutType layoutType, JPanel container) {
		switch (layoutType) {
			case GRID:
			case SIMPLE:
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
