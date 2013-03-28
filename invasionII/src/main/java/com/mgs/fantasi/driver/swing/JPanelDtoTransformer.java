package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelDto;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelLayoutTranslator;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelWithDifferentShape;
import com.mgs.fantasi.properties.JPanelDecorator;
import com.mgs.fantasi.properties.UIPropertiesProvider;
import com.mgs.fantasi.properties.data.Padding;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.structure.treeAux.WireframeLayoutType;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JPanelDtoTransformer {

	private final JPanelLayoutTranslator jPanelLayoutTranslator;
	private final JPanelDecorator<Padding> paddingDecorator;

	public JPanelDtoTransformer(JPanelDecorator<Padding> paddingDecorator, JPanelLayoutTranslator jPanelLayoutTranslator) {
		this.paddingDecorator = paddingDecorator;
		this.jPanelLayoutTranslator = jPanelLayoutTranslator;
	}

	JPanel translateDto(JPanelDto dto) {
		UIPropertiesProvider uiProperties = dto.getUiProperties();

		JPanel undecorated = createBaseJPanel(dto.getShape(), dto.getType(), uiProperties, dto.getChildren());
		if (uiProperties.getPadding().isFullyDefined()) {
			return paddingDecorator.decorate(undecorated, uiProperties.getPadding().getValue());
		}
		return undecorated;
	}

	private JPanel createBaseJPanel(PolygonPointsIterator shape, WireframeLayoutType type, UIPropertiesProvider uiProperties, List<JPanelDto.JPanelChild> children) {
		return shape.isRectangular() ?
				createRectangleBase(type, uiProperties, children) :
				createNonRectangularBase(type, uiProperties, shape);
	}

	private JPanel createRectangleBase(WireframeLayoutType layoutType, UIPropertiesProvider uiProperties, List<JPanelDto.JPanelChild> children) {
		JPanel jPanel = new JPanel();
		jPanel.setOpaque(false);
		SwingUtils.applyUIProperties(jPanel, uiProperties);
		if (layoutType != WireframeLayoutType.EMPTY) {
			LayoutManager layout = jPanelLayoutTranslator.translate(layoutType, jPanel);
			jPanel.setLayout(layout);
			for (JPanelDto.JPanelChild child : children) {
				JPanelDto panel = child.getPanel();
				jPanel.add(createBaseJPanel(panel.getShape(), panel.getType(), panel.getUiProperties(), panel.getChildren()), JPanelDto.translate(child.getCollocationInfo(), layout));
			}
		}
		return jPanel;
	}

	private JPanel createNonRectangularBase(WireframeLayoutType layoutType, UIPropertiesProvider uiProperties, PolygonPointsIterator shape) {
		JPanelWithDifferentShape container = new JPanelWithDifferentShape(shape, uiProperties);
		if (layoutType != WireframeLayoutType.EMPTY) {
			container.setLayout(jPanelLayoutTranslator.translate(layoutType, container));
		}
		return container;
	}
}
