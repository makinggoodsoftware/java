package com.mgs.fantasi.properties;

import com.mgs.fantasi.views.BaseView;
import org.junit.Test;

import static com.mgs.fantasi.properties.BorderDefinition.noBorder;
import static com.mgs.fantasi.properties.ColorDefinition.noColor;
import static com.mgs.fantasi.properties.measurements.EmptyMeasurement.emptyMeasurement;
import static com.mgs.fantasi.rendering.Padding.noPadding;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class UIPropertiesTest {
	@Test
	public void testDefaultValues (){
		UIProperties uiProperties = new UIProperties();

		assertEquals(uiProperties.getBackgroundColor(), noColor());
		assertEquals(uiProperties.getBorder(), noBorder());
		assertEquals(uiProperties.getMeasurement(), emptyMeasurement());
		assertEquals(uiProperties.getName(), "");
		assertEquals(uiProperties.getPadding(), noPadding());
		assertTrue(uiProperties.getShape() instanceof BaseView.NativeRectanguarShape);
	}

}
