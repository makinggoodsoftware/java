package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.views.View;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.*;

public class SwingUINativeRendererTest {
	private SwingUINativeRenderer swingUINativeRenderer;
	private SwingUINativeRenderer.RenderingProcessFactory renderingProcessFactoryMock;

	@Before
	public void setUp() throws Exception {
		renderingProcessFactoryMock = Mockito.mock(SwingUINativeRenderer.RenderingProcessFactory.class);
		swingUINativeRenderer = new SwingUINativeRenderer(renderingProcessFactoryMock);
	}

	//TODO We need to complete this test... Can we unit test this?
	@Test @Ignore
	public void testRender() throws Exception {
		View viewMock = Mockito.mock(View.class);
		UIProfile uiProfileMock = Mockito.mock(UIProfile.class);

		JPanel jPanel = swingUINativeRenderer.render(viewMock, uiProfileMock);
	}
}