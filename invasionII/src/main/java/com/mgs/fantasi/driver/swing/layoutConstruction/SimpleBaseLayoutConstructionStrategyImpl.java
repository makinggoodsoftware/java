package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.properties.measurements.Fractions;
import com.mgs.fantasi.rendering.wireframe.RectangleWireframe;
import com.mgs.fantasi.views.View;

import java.awt.*;

import static com.mgs.fantasi.driver.swing.SwingUINativeRenderer.coordinates;

public class SimpleBaseLayoutConstructionStrategyImpl extends BaseLayoutConstructionStrategyStrategy<GridBagConstraints> {
	public SimpleBaseLayoutConstructionStrategyImpl(LayoutProvider layoutProvider) {
		super(layoutProvider);
	}

	public SimpleBaseLayoutConstructionStrategyImpl from(RectangleWireframe<View> content) {
		View renderable = content.getContent();
		if (renderable!=null) {
			queueForAddition(renderable).into(coordinates(0, 0, Fractions.all(), Fractions.all()));
		}
		return this;
	}
}
