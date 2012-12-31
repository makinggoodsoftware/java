package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.properties.measurements.Fractions;
import com.mgs.fantasi.rendering.wireframe.RectangleWireframe;
import com.mgs.fantasi.views.View;

import java.awt.*;

import static com.mgs.fantasi.driver.swing.SwingUINativeRenderer.coordinates;

public class SimpleBaseLayoutConstructionStrategyImpl extends BaseLayoutConstructionStrategyStrategy<GridBagConstraints, RectangleWireframe> {
	public SimpleBaseLayoutConstructionStrategyImpl(LayoutProvider layoutProvider) {
		super(layoutProvider);
	}

	@Override
    public SimpleBaseLayoutConstructionStrategyImpl fillWith(RectangleWireframe content) {
		View renderable = content.getContent();
		if (renderable!=null) {
			queueForAddition(renderable).into(coordinates(0, 0, Fractions.all(), Fractions.all()));
		}
		return this;
	}
}
