package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.properties.measurements.Fractions;
import com.mgs.fantasi.rendering.Renderable;
import com.mgs.fantasi.rendering.structure.SimpleStructure;

import java.awt.*;

import static com.mgs.fantasi.driver.swing.SwingUINativeRenderer.coordinates;

public class SimpleBaseLayoutConstructionStrategyImpl extends BaseLayoutConstructionStrategyStrategy<GridBagConstraints> {
	public SimpleBaseLayoutConstructionStrategyImpl(LayoutProvider layoutProvider) {
		super(layoutProvider);
	}

	public SimpleBaseLayoutConstructionStrategyImpl from(SimpleStructure<Renderable> content) {
		Renderable renderable = content.getContent();
		if (renderable!=null) {
			queueForAddition(renderable).into(coordinates(0, 0, Fractions.all(), Fractions.all()));
		}
		return this;
	}
}
