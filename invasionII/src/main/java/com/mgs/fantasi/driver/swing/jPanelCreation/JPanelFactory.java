package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.UIPropertiesProvider;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.structure.treeAux.WireframeLayoutType;

import javax.swing.*;
import java.util.List;

public interface JPanelFactory {
	JPanel create(WireframeLayoutType layoutType, UIPropertiesProvider uiProperties, JPanelLayoutTranslator jPanelLayoutTranslator1, List<JPanelDto.JPanelChild> children, PolygonPointsIterator shape);
}
