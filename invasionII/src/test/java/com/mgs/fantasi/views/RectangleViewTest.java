package com.mgs.fantasi.views;

import com.mgs.fantasi.rendering.structure.StructureType;
import com.mgs.fantasi.rendering.wireframe.RectangleWireframe;
import junit.framework.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static com.mgs.fantasi.views.RectangleView.rectangle;

public class RectangleViewTest {
	private View contentMock = Mockito.mock(View.class);

	@Test
	public void childViews_forEmptyRectangleView_shouldReturnAnEmptyRectangleWireframe(){
		RectangleView rectangle = rectangle();
		RectangleWireframe<View> wireframe = (RectangleWireframe<View>) rectangle.buildContent();

		Assert.assertEquals(wireframe.getType(), StructureType.SIMPLE);
		Assert.assertNull(wireframe.getContent());
	}

	@Test
	public void childViews_forRectangleViewWithContent_shouldReturnRectangleWireframeWithContent(){
		RectangleView rectangle = rectangle().withContent(contentMock);
		RectangleWireframe<View> wireframe = (RectangleWireframe<View>) rectangle.buildContent();

		Assert.assertEquals(wireframe.getType(), StructureType.SIMPLE);
		Assert.assertSame(wireframe.getContent(), contentMock);
	}

}
