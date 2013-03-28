package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingManager;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelDto;
import com.mgs.fantasi.profile.UIProfile;
import com.mgs.fantasi.properties.UIPropertiesManager;
import com.mgs.fantasi.properties.UIPropertiesProvider;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.structure.CollocationInfo;
import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.structure.treeAux.WireframeNode;

import javax.swing.*;
import java.util.Map;

public class JPanelRenderingManager implements RenderingManager<JPanel> {
	private final UIPropertiesManager uiPropertiesManager;
	private final JPanelDtoTransformer jPanelDtoTransformer;


	public JPanelRenderingManager(UIPropertiesManager uiPropertiesManager, JPanelDtoTransformer jPanelDtoTransformer) {
		this.uiPropertiesManager = uiPropertiesManager;
		this.jPanelDtoTransformer = jPanelDtoTransformer;
	}

	@Override
	public JPanel render(Structure structure, UIProfile uiProfile) {
		JPanelDto dto = createDto(structure, uiProfile);
		return jPanelDtoTransformer.translateDto(dto);
	}

	private JPanelDto createDto(Structure structure, UIProfile uiProfile) {
		WireframeNode root = structure.getRoot();

		UIPropertiesProvider withStylesApplied = uiPropertiesManager.applyStyles(root.getUiProperties(), uiProfile.findStylesFor(root));
		PolygonPointsIterator shape = root.getShape();
		JPanelDto containerJPanelDto = new JPanelDto(withStylesApplied, shape, root.getType());

		for (Map.Entry<CollocationInfo, Structure> childNode : structure.getChildren().entrySet()) {
			JPanelDto childJPanelDto = createDto(childNode.getValue(), uiProfile);
			containerJPanelDto.addChild(childJPanelDto, childNode.getKey());
		}

		return containerJPanelDto;
	}


}
