package com.mgs.fantasi.properties;

import com.mgs.fantasi.profile.UIStyle;
import com.mgs.fantasi.rendering.Padding;
import com.mgs.fantasi.views.BaseView;
import org.junit.Test;

import static com.mgs.fantasi.properties.BorderDefinition.zero;
import static com.mgs.fantasi.properties.ColorDefinition.colorFromAwtColor;
import static com.mgs.fantasi.properties.ColorDefinition.transparent;
import static com.mgs.fantasi.properties.measurements.EmptyMeasurement.emptyMeasurement;
import static java.awt.Color.BLACK;
import static java.awt.Color.RED;
import static java.awt.Color.YELLOW;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class UIPropertiesTest {
	@Test
	public void testDefaultValues (){
		UIProperties uiProperties = new UIProperties();

		assertEquals(uiProperties.getBackgroundColor(), transparent());
		assertEquals(uiProperties.getBorder(), zero());
		assertEquals(uiProperties.getMeasurement(), emptyMeasurement());
		assertEquals(uiProperties.getName(), "");
		assertEquals(uiProperties.getPadding(), Padding.zero());
		assertTrue(uiProperties.getShape() instanceof BaseView.NativeRectanguarShape);
	}

	@Test
	public void testApplyStyle_whenPropertiesAndStyleAreCompletelyEmpty(){
		UIProperties uiProperties = new UIProperties();
		uiProperties.applyStyle(new UIStyle());

		assertEquals(uiProperties.getBackgroundColor(), transparent());
		assertEquals(uiProperties.getBorder(), zero());
	}

	@Test
	public void testApplyStyle_whenPropertiesAreEmptyAndStyleIsFullyDefined(){
		UIProperties uiProperties = new UIProperties();
		UIStyle uiStyle = new UIStyle().
				withBackgroundColor(colorFromAwtColor(YELLOW)).
			withBorder(new BorderDefinition(colorFromAwtColor(RED), 3));
		uiProperties.applyStyle(uiStyle);

		assertEquals(uiProperties.getBackgroundColor(), colorFromAwtColor(YELLOW));
		assertEquals(uiProperties.getBorder().getColor(), colorFromAwtColor(RED));
		assertEquals(uiProperties.getBorder().getWidth(), 3);
	}

	@Test
	public void testApplyStyle_whenPropertiesAreEmptyAndStyleIsPartiallyDefined(){
		UIProperties uiProperties = new UIProperties();
		UIStyle uiStyle = new UIStyle().
				withBackgroundColor(colorFromAwtColor(YELLOW));
		uiProperties.applyStyle(uiStyle);

		assertEquals(uiProperties.getBackgroundColor(), colorFromAwtColor(YELLOW));
		assertEquals(uiProperties.getBorder(), zero());
	}


	@Test
	public void testApplyStyle_whenPropertiesAreFullyPopulatedAndStyleIsPartiallyDefined (){
		UIProperties uiProperties = new UIProperties();
		uiProperties.setBackgroundColor(colorFromAwtColor(RED));
		uiProperties.setBorder(new BorderDefinition(colorFromAwtColor(YELLOW), 4));

		UIStyle uiStyle = new UIStyle().
				withBackgroundColor(colorFromAwtColor(BLACK));
		uiProperties.applyStyle(uiStyle);

		assertEquals(uiProperties.getBackgroundColor(), colorFromAwtColor(BLACK));
		assertEquals(uiProperties.getBorder().getColor(), colorFromAwtColor(YELLOW));
		assertEquals(uiProperties.getBorder().getWidth(), 4);
	}

	@Test
	public void testApplyStyle_whenPropertiesArePartiallyPopulatedAndStyleIsPartiallyDefined (){
		UIProperties uiProperties = new UIProperties();
		uiProperties.setBackgroundColor(colorFromAwtColor(RED));

		UIStyle uiStyle = new UIStyle().
				withBorder(new BorderDefinition(colorFromAwtColor(BLACK), 2));
		uiProperties.applyStyle(uiStyle);

		assertEquals(uiProperties.getBackgroundColor(), colorFromAwtColor(RED));
		assertEquals(uiProperties.getBorder().getColor(), colorFromAwtColor(BLACK));
		assertEquals(uiProperties.getBorder().getWidth(), 2);

	}
}
