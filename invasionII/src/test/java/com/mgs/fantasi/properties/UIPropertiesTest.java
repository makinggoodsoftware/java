package com.mgs.fantasi.properties;

import com.mgs.fantasi.properties.polygon.NativeRectanguarShape;
import com.mgs.fantasi.styles.StyleManagerImpl;
import com.mgs.fantasi.styles.UIStyle;
import org.junit.Test;

import static com.mgs.fantasi.properties.BorderFactory.newBorder;
import static com.mgs.fantasi.properties.BorderFactory.noBorder;
import static com.mgs.fantasi.properties.ColorFactory.newColorFromAwt;
import static com.mgs.fantasi.properties.ColorFactory.transparent;
import static com.mgs.fantasi.properties.measurements.EmptyMeasurement.emptyMeasurement;
import static java.awt.Color.*;
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

	@Test
	public void testApplyStyle_whenPropertiesAndStyleAreCompletelyEmpty(){
		UIProperties uiProperties = new UIProperties();
		new StyleManagerImpl().applyStyle(uiProperties, new UIStyle());

		assertEquals(uiProperties.getBackgroundColor(), transparent());
		assertEquals(uiProperties.getBorder(), noBorder());
	}

	@Test
	public void testApplyStyle_whenPropertiesAreEmptyAndStyleIsFullyDefined(){
		UIProperties uiProperties = new UIProperties();
		UIStyle uiStyle = new UIStyle().
			withBackgroundColor(newColorFromAwt(YELLOW)).
			withBorder(newBorder(newColorFromAwt(RED), 3));
		new StyleManagerImpl().applyStyle(uiProperties, uiStyle);

		assertEquals(uiProperties.getBackgroundColor(), newColorFromAwt(YELLOW));
		assertEquals(uiProperties.getBorder().getData().getColor(), newColorFromAwt(RED));
		assertEquals(uiProperties.getBorder().getData().getWidth(), 3);
	}

	@Test
	public void testApplyStyle_whenPropertiesAreEmptyAndStyleIsPartiallyDefined(){
		UIProperties uiProperties = new UIProperties();
		UIStyle uiStyle = new UIStyle().
			withBackgroundColor(newColorFromAwt(YELLOW));
		new StyleManagerImpl().applyStyle(uiProperties, uiStyle);

		assertEquals(uiProperties.getBackgroundColor(), newColorFromAwt(YELLOW));
		assertEquals(uiProperties.getBorder(), noBorder());
	}


	@Test
	public void testApplyStyle_whenPropertiesAreFullyPopulatedAndStyleIsPartiallyDefined (){
		UIProperties uiProperties = new UIProperties();
		uiProperties.setBackgroundColor(newColorFromAwt(RED));
		uiProperties.setBorder(newBorder(newColorFromAwt(YELLOW), 4));

		UIStyle uiStyle = new UIStyle().
			withBackgroundColor(newColorFromAwt(BLACK));
		new StyleManagerImpl().applyStyle(uiProperties, uiStyle);

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
		new StyleManagerImpl().applyStyle(uiProperties, uiStyle);

		assertEquals(uiProperties.getBackgroundColor(), newColorFromAwt(RED));
		assertEquals(uiProperties.getBorder().getData().getColor(), newColorFromAwt(BLACK));
		assertEquals(uiProperties.getBorder().getData().getWidth(), 2);

	}
}
