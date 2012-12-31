package com.mgs.fantasi.properties;

import com.mgs.fantasi.properties.polygon.NativeRectanguarShape;
import org.junit.Test;

import static com.mgs.fantasi.properties.BorderFactory.noBorder;
import static com.mgs.fantasi.properties.ColorFactory.transparent;
import static com.mgs.fantasi.properties.measurements.EmptyMeasurement.emptyMeasurement;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class UIPropertiesTest {
	@Test
	public void testDefaultValues (){
		UIProperties uiProperties = new UIProperties();

		assertEquals(uiProperties.getBackgroundColor(), transparent());
		assertEquals(uiProperties.getBorder(), noBorder());
		assertEquals(uiProperties.getMeasurement(), emptyMeasurement());
		assertEquals(uiProperties.getPadding(), Padding.zero());
		assertTrue(uiProperties.getShape() instanceof NativeRectanguarShape);
	}
}
