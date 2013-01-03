package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.UINativeRenderer;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.views.View;

import javax.swing.*;

public final class SwingUINativeRenderer implements UINativeRenderer<JPanel> {
	private final RenderingContextFactory renderingContextFactory;

	public SwingUINativeRenderer(RenderingContextFactory renderingContextFactory) {
		this.renderingContextFactory = renderingContextFactory;
	}

	@Override
	public JPanel render(View view, UIProfile uiProfile) {
		return renderingContextFactory.getContextFor(uiProfile).render(view);
	}


}
