package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingManager;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelDto;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelDtoFactory;
import com.mgs.fantasi.profile.UIProfile;
import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.structure.CollocationInfo;
import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.structure.treeAux.WireframeNode;

import javax.swing.*;
import java.util.Map;

import static com.mgs.fantasi.properties.UIPropertiesBuilderFactory.from;

public class JPanelRenderingManager implements RenderingManager<JPanel> {
	private final JPanelDtoFactory jPanelDtoFactory;


	public JPanelRenderingManager(JPanelDtoFactory jPanelDtoFactory) {
		this.jPanelDtoFactory = jPanelDtoFactory;
	}

	@Override
	public JPanel render(Structure structure, UIProfile uiProfile) {
		return createDto(structure, uiProfile).build(structure.getType());
	}

	private JPanelDto createDto(Structure structure, UIProfile uiProfile) {
		JPanelDto jPanelDto = createContainerDto(structure.getRoot(), uiProfile);

		for (Map.Entry<CollocationInfo, Structure> childNode : structure.getChildren().entrySet()) {
			JPanelDto child = createDto(childNode.getValue(), uiProfile);
			jPanelDto.addChild(child, childNode.getKey(), childNode.getValue().getType());
		}

		return jPanelDto;
	}

	private JPanelDto createContainerDto(WireframeNode wireframe, UIProfile uiProfile) {
		UIPropertiesBuilder withStylesApplied = from(wireframe.getValue().getUiProperties());
		withStylesApplied.applyStyles(uiProfile.findStylesFor(wireframe));
		return jPanelDtoFactory.forUIProperties(withStylesApplied.build(), wireframe.getShape());
	}


}
