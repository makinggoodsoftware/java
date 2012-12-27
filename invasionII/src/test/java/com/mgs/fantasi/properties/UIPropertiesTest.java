package com.mgs.fantasi.properties;

import com.mgs.fantasi.profile.UIStyle;
import com.mgs.fantasi.rendering.Padding;
import com.mgs.fantasi.views.BaseView;
import org.junit.Test;

import static com.mgs.fantasi.properties.BorderDefinition.newBorder;
import static com.mgs.fantasi.properties.BorderDefinition.zero;
import static com.mgs.fantasi.properties.ColorFactory.newColorFromAwt;
import static com.mgs.fantasi.properties.ColorFactory.newTransparentColor;
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

		assertEquals(uiProperties.getBackgroundColor(), newTransparentColor());
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

		assertEquals(uiProperties.getBackgroundColor(), newTransparentColor());
		assertEquals(uiProperties.getBorder(), zero());
	}

	@Test
	public void testApplyStyle_whenPropertiesAreEmptyAndStyleIsFullyDefined(){
		UIProperties uiProperties = new UIProperties();
		UIStyle uiStyle = new UIStyle().
			withBackgroundColor(newColorFromAwt(YELLOW)).
			withBorder(newBorder(newColorFromAwt(RED), 3));
		uiProperties.applyStyle(uiStyle);

		assertEquals(uiProperties.getBackgroundColor(), newColorFromAwt(YELLOW));
		assertEquals(uiProperties.getBorder().getData().getColor(), newColorFromAwt(RED));
		assertEquals(uiProperties.getBorder().getData().getWidth(), 3);
	}

	@Test
	public void testApplyStyle_whenPropertiesAreEmptyAndStyleIsPartiallyDefined(){
		UIProperties uiProperties = new UIProperties();
		UIStyle uiStyle = new UIStyle().
			withBackgroundColor(newColorFromAwt(YELLOW));
		uiProperties.applyStyle(uiStyle);

		assertEquals(uiProperties.getBackgroundColor(), newColorFromAwt(YELLOW));
		assertEquals(uiProperties.getBorder(), zero());
	}


	@Test
	public void testApplyStyle_whenPropertiesAreFullyPopulatedAndStyleIsPartiallyDefined (){
		UIProperties uiProperties = new UIProperties();
		uiProperties.setBackgroundColor(newColorFromAwt(RED));
		uiProperties.setBorder(newBorder(newColorFromAwt(YELLOW), 4));

		UIStyle uiStyle = new UIStyle().
			withBackgroundColor(newColorFromAwt(BLACK));
		uiProperties.applyStyle(uiStyle);

		assertEquals(uiProperties.getBackgroundColor(), newColorFromAwt(BLACK));
		assertEquals(uiProperties.getBorder().getData().getColor(), newColorFromAwt(YELLOW));
		assertEquals(uiProperties.getBorder().getData().getWidth(), 4);
	}

	@Test
	public void testApplyStyle_whenPropertiesArePartiallyPopulatedAndStyleIsPartiallyDefined (){
		UIProperties uiProperties = new UIProperties();
		uiProperties.setBackgroundColor(newColorFromAwt(RED));

		UIStyle uiStyle = new UIStyle().
			withBorder(newBorder(newColorFromAwt(BLACK), 2));
		uiProperties.applyStyle(uiStyle);

		assertEquals(uiProperties.getBackgroundColor(), newColorFromAwt(RED));
		assertEquals(uiProperties.getBorder().getData().getColor(), newColorFromAwt(BLACK));
		assertEquals(uiProperties.getBorder().getData().getWidth(), 2);

	}
}
