package com.mgs.fantasi.properties;

import com.mgs.fantasi.profile.UIStyle;
import com.mgs.fantasi.rendering.Padding;
import com.mgs.fantasi.views.BaseView;
import org.junit.Test;

import static com.mgs.fantasi.properties.BorderDefinition.newBorder;
import static com.mgs.fantasi.properties.BorderDefinition.zero;
import static com.mgs.fantasi.properties.ColorDefinition.newColor;
import static com.mgs.fantasi.properties.ColorDefinition.newTransparent;
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

		assertEquals(uiProperties.getBackgroundColor(), newTransparent());
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

		assertEquals(uiProperties.getBackgroundColor(), newTransparent());
		assertEquals(uiProperties.getBorder(), zero());
	}

	@Test
	public void testApplyStyle_whenPropertiesAreEmptyAndStyleIsFullyDefined(){
		UIProperties uiProperties = new UIProperties();
		UIStyle uiStyle = new UIStyle().
			withBackgroundColor(newColor(YELLOW)).
			withBorder(newBorder(newColor(RED), 3));
		uiProperties.applyStyle(uiStyle);

		assertEquals(uiProperties.getBackgroundColor(), newColor(YELLOW));
		assertEquals(uiProperties.getBorder().getData().getColor(), newColor(RED));
		assertEquals(uiProperties.getBorder().getData().getWidth(), 3);
	}

	@Test
	public void testApplyStyle_whenPropertiesAreEmptyAndStyleIsPartiallyDefined(){
		UIProperties uiProperties = new UIProperties();
		UIStyle uiStyle = new UIStyle().
			withBackgroundColor(newColor(YELLOW));
		uiProperties.applyStyle(uiStyle);

		assertEquals(uiProperties.getBackgroundColor(), newColor(YELLOW));
		assertEquals(uiProperties.getBorder(), zero());
	}


	@Test
	public void testApplyStyle_whenPropertiesAreFullyPopulatedAndStyleIsPartiallyDefined (){
		UIProperties uiProperties = new UIProperties();
		uiProperties.setBackgroundColor(newColor(RED));
		uiProperties.setBorder(newBorder(newColor(YELLOW), 4));

		UIStyle uiStyle = new UIStyle().
			withBackgroundColor(newColor(BLACK));
		uiProperties.applyStyle(uiStyle);

		assertEquals(uiProperties.getBackgroundColor(), newColor(BLACK));
		assertEquals(uiProperties.getBorder().getData().getColor(), newColor(YELLOW));
		assertEquals(uiProperties.getBorder().getData().getWidth(), 4);
	}

	@Test
	public void testApplyStyle_whenPropertiesArePartiallyPopulatedAndStyleIsPartiallyDefined (){
		UIProperties uiProperties = new UIProperties();
		uiProperties.setBackgroundColor(newColor(RED));

		UIStyle uiStyle = new UIStyle().
			withBorder(newBorder(newColor(BLACK), 2));
		uiProperties.applyStyle(uiStyle);

		assertEquals(uiProperties.getBackgroundColor(), newColor(RED));
		assertEquals(uiProperties.getBorder().getData().getColor(), newColor(BLACK));
		assertEquals(uiProperties.getBorder().getData().getWidth(), 2);

	}
}
