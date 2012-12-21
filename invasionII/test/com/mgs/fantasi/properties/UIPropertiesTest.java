package com.mgs.fantasi.properties;

import com.mgs.fantasi.profile.UIStyle;
import com.mgs.fantasi.views.BaseView;
import org.junit.Test;

import static com.mgs.fantasi.properties.BorderDefinition.noBorder;
import static com.mgs.fantasi.properties.ColorDefinition.colorFromAwtColor;
import static com.mgs.fantasi.properties.ColorDefinition.noColor;
import static com.mgs.fantasi.properties.measurements.EmptyMeasurement.emptyMeasurement;
import static com.mgs.fantasi.rendering.Padding.noPadding;
import static java.awt.Color.RED;
import static java.awt.Color.YELLOW;
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

	@Test
	public void testApplyStyle_whenPropertiesAndStyleAreCompletelyEmpty(){
		UIProperties uiProperties = new UIProperties();
		uiProperties.applyStyle(new UIStyle());

		assertEquals(uiProperties.getBackgroundColor(), noColor());
		assertEquals(uiProperties.getBorder(), noBorder());
	}

	@Test
	public void testApplyStyle_whenPropertiesAreEmptyAndStyleIsFullyDefined(){
		UIProperties uiProperties = new UIProperties();
		UIStyle uiStyle = new UIStyle().
			withBackground(colorFromAwtColor(YELLOW)).
			withBorder(new BorderDefinition(colorFromAwtColor(RED), 3));
		uiProperties.applyStyle(uiStyle);

		assertEquals(uiProperties.getBackgroundColor(), colorFromAwtColor(YELLOW));
		assertEquals(uiProperties.getBorder().getColor(), colorFromAwtColor(RED));
		assertEquals(uiProperties.getBorder().getWidth(), 3);
	}


}
