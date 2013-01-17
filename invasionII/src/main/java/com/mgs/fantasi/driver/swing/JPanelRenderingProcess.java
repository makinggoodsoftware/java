package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingProcess;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategy;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.WireframeContentType;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JPanelRenderingProcess implements RenderingProcess<JPanel> {
	private final JPanelCreationStrategy baseCreationStrategy;
	private final List<ToBeAdded<JPanel>> renderingContent;

	public JPanelRenderingProcess(JPanelCreationStrategy baseCreationStrategy, List<ToBeAdded<JPanel>> renderingContent) {
		this.baseCreationStrategy = baseCreationStrategy;
		this.renderingContent = renderingContent;
	}

	@Override
	public JPanel render() {
		JPanel container = baseCreationStrategy.create();
		if (renderingContent.isEmpty()) return container;

		LayoutManager layoutManager = translateTypeIntoLayout(container, baseCreationStrategy.getContentType());
		container.setLayout(layoutManager);
		for (ToBeAdded<JPanel> toBeAdded : renderingContent) {
			RenderingProcess<JPanel> childRenderingProcess = toBeAdded.getRenderingProcess();
			CollocationInfo specifics = toBeAdded.getSpecifics();

			JPanel child = childRenderingProcess.render();
			container.add(child, translate(specifics, layoutManager));
		}
		return container;
	}

	private Object translate(CollocationInfo specifics, LayoutManager type) {
		if (type instanceof GridBagLayout) {
			return SwingUtils.coordinates(specifics.getCoordinateX(), specifics.getCoordinateY(), specifics.getProportionOfParentWeight(), specifics.getProportionOfParentHeight());
		} else if (type instanceof OverlayLayout) {
			return specifics.getzIndex();
		}
		throw new RuntimeException("Not expected to hit this code point");
	}

	private LayoutManager translateTypeIntoLayout(JPanel container, WireframeContentType contentType) {
		switch (contentType) {
			case GRID:
			case RECTANGLE:
				return new GridBagLayout();
			case LAYERS:
				return new OverlayLayout(container);
			default:
				throw new RuntimeException("Shouldn't have reached this point in the code!!!");
		}
	}
}
