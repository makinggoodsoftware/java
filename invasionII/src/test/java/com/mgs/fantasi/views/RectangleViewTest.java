package com.mgs.fantasi.views;

import com.mgs.fantasi.wireframe.RectangleWireframe;
import com.mgs.fantasi.wireframe.WireframeType;
import junit.framework.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static com.mgs.fantasi.views.RectangleView.rectangle;

public class RectangleViewTest {
	private View contentMock = Mockito.mock(View.class);

	@Test
	public void childViews_forEmptyRectangleView_shouldReturnAnEmptyRectangleWireframe() {
		RectangleView rectangle = rectangle();
		RectangleWireframe wireframe = (RectangleWireframe) rectangle.buildContent();

		Assert.assertEquals(wireframe.getType(), WireframeType.SIMPLE);
		Assert.assertNull(wireframe.getContent());
	}

	@Test
	public void childViews_forRectangleViewWithContent_shouldReturnRectangleWireframeWithContent() {
		RectangleView rectangle = rectangle().withContent(contentMock);
		RectangleWireframe wireframe = (RectangleWireframe) rectangle.buildContent();

		Assert.assertEquals(wireframe.getType(), WireframeType.SIMPLE);
		Assert.assertSame(wireframe.getContent(), contentMock);
	}

}
