package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingManager;
import com.mgs.fantasi.driver.swing.jPanelCreation.*;
import com.mgs.fantasi.profile.UIProfile;
import com.mgs.fantasi.properties.JPanelDecorator;
import com.mgs.fantasi.properties.UIPropertiesManager;
import com.mgs.fantasi.properties.UIPropertiesProvider;
import com.mgs.fantasi.properties.data.Padding;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.structure.CollocationInfo;
import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.structure.treeAux.WireframeNode;

import javax.swing.*;
import java.util.Map;

public class JPanelRenderingManager implements RenderingManager<JPanel> {
	private final UIPropertiesManager uiPropertiesManager;
	private final JPanelLayoutTranslator jPanelLayoutTranslator;
	private final JPanelDecorator<Padding> paddingDecorator;


	public JPanelRenderingManager(UIPropertiesManager uiPropertiesManager, JPanelLayoutTranslator jPanelLayoutTranslator, JPanelDecorator<Padding> paddingDecorator) {
		this.uiPropertiesManager = uiPropertiesManager;
		this.jPanelLayoutTranslator = jPanelLayoutTranslator;
		this.paddingDecorator = paddingDecorator;
	}

	@Override
	public JPanel render(Structure structure, UIProfile uiProfile) {
		return createDto(structure, uiProfile).build(structure.getType());
	}

	private JPanelDto createDto(Structure structure, UIProfile uiProfile) {
		WireframeNode root = structure.getRoot();

		UIPropertiesProvider withStylesApplied = uiPropertiesManager.applyStyles(root.getUiProperties(), uiProfile.findStylesFor(root));
		PolygonPointsIterator shape = root.getShape();
		JPanelFactory jPanelFactory = shape.isRectangular() ? new StandardJPanelFactory() : new NonRectangularJPanelFactory();
		JPanelDto containerJPanelDto = new JPanelDto(withStylesApplied, jPanelLayoutTranslator, shape, jPanelFactory, paddingDecorator);

		for (Map.Entry<CollocationInfo, Structure> childNode : structure.getChildren().entrySet()) {
			JPanelDto childJPanelDto = createDto(childNode.getValue(), uiProfile);
			containerJPanelDto.addChild(childJPanelDto, childNode.getKey(), childNode.getValue().getType());
		}

		return containerJPanelDto;
	}


}
