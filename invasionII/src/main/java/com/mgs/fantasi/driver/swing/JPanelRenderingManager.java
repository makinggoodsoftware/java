package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingManager;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelDto;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelDtoFactory;
import com.mgs.fantasi.profile.UIProfile;
import com.mgs.fantasi.properties.UIPropertiesManager;
import com.mgs.fantasi.properties.UIPropertiesProvider;
import com.mgs.fantasi.structure.CollocationInfo;
import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.structure.treeAux.WireframeNode;

import javax.swing.*;
import java.util.Map;

public class JPanelRenderingManager implements RenderingManager<JPanel> {
	private final JPanelDtoFactory jPanelDtoFactory;
	private final UIPropertiesManager uiPropertiesManager;


	public JPanelRenderingManager(JPanelDtoFactory jPanelDtoFactory, UIPropertiesManager uiPropertiesManager) {
		this.jPanelDtoFactory = jPanelDtoFactory;
		this.uiPropertiesManager = uiPropertiesManager;
	}

	@Override
	public JPanel render(Structure structure, UIProfile uiProfile) {
		return createDto(structure, uiProfile).build(structure.getType());
	}

	private JPanelDto createDto(Structure structure, UIProfile uiProfile) {
		WireframeNode root = structure.getRoot();

		UIPropertiesProvider withStylesApplied = uiPropertiesManager.applyStyles(root.getUiProperties(), uiProfile.findStylesFor(root));
		JPanelDto jPanelDto = jPanelDtoFactory.forUIProperties(withStylesApplied, root.getShape());

		for (Map.Entry<CollocationInfo, Structure> childNode : structure.getChildren().entrySet()) {
			JPanelDto child = createDto(childNode.getValue(), uiProfile);
			jPanelDto.addChild(child, childNode.getKey(), childNode.getValue().getType());
		}

		return jPanelDto;
	}


}
