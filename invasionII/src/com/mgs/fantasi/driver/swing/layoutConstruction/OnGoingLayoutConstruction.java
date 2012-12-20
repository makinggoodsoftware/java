package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.driver.swing.SwingUINativeRenderer;
import com.mgs.fantasi.rendering.Renderable;
import com.mgs.fantasi.rendering.structure.grid.GridStructure;

import javax.swing.*;

public interface OnGoingLayoutConstruction<T> {
	OnGoingLayoutConstruction<T> processGridStructure(GridStructure<Renderable> structure);

	OnGoingChildContentConstruction<T> add(Renderable child);

	void buildInto(JPanel container, SwingUINativeRenderer renderer);
}
