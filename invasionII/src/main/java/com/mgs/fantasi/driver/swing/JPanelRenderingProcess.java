package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingProcess;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategy;
import com.mgs.fantasi.wireframe.Placeholder;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeType;

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

		LayoutManager layoutManager = translateTypeIntoLayout(container, baseCreationStrategy.getType());
		container.setLayout(layoutManager);
		for (ToBeAdded<JPanel> toBeAdded : renderingContent) {
			RenderingProcess<JPanel> childRenderingProcess = toBeAdded.getRenderingProcess();
			Placeholder<Wireframe> specifics = toBeAdded.getSpecifics();

			JPanel child = childRenderingProcess.render();
			container.add(child, translate(specifics, layoutManager));
		}
		return container;
	}

	private Object translate(Placeholder<Wireframe> specifics, LayoutManager type) {
		if (type instanceof GridBagLayout) {
			return SwingUtils.coordinates(specifics.getCoodinateX(), specifics.getCoodinateY(), specifics.getProportionOfParentWeight(), specifics.getProportionOfParentHeight());
		} else if (type instanceof OverlayLayout) {
			return specifics.getzIndex();
		}
		throw new RuntimeException("Not expected to hit this code point");
	}

	private LayoutManager translateTypeIntoLayout(JPanel container, WireframeType type) {
		switch (type) {
			case GRID:
			case SIMPLE:
				return new GridBagLayout();
			case LAYERS:
				return new OverlayLayout(container);
			default:
				throw new RuntimeException("Shouldn't have reached this point in the code!!!");
		}
	}
}
