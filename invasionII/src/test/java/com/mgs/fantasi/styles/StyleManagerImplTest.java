package com.mgs.fantasi.styles;

import org.junit.Before;
import org.junit.Test;

public class StyleManagerImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testApplyStyle_whenPropertiesAndStyleAreCompletelyEmpty() {
//		UIProperties uiProperties = new UIProperties(border, backgroundColor, padding, shape);
//		styleManager.applyStyles(uiProperties, asSet(new UIStyle()));
//
//		assertEquals(uiProperties.getBackgroundColor(), transparent());
//		assertEquals(uiProperties.getBorder(), noBorder());
	}

	@Test
	public void testApplyStyle_whenPropertiesAreEmptyAndStyleIsFullyDefined() {
//		UIProperties uiProperties = new UIProperties(border, backgroundColor, padding, shape);
//		UIStyle uiStyle = new UIStyle().
//				withBackgroundColor(newColorFromAwt(YELLOW)).
//				withBorder(newBorder(newColorFromAwt(RED), 3));
//		UIProperties propertiesWithStyleApplied = styleManager.applyStyles(uiProperties, asSet(uiStyle));
//
//		assertEquals(propertiesWithStyleApplied.getBackgroundColor(), newColorFromAwt(YELLOW));
//		assertEquals(propertiesWithStyleApplied.getBorder().getValue().getColor(), newColorFromAwt(RED));
//		assertEquals(propertiesWithStyleApplied.getBorder().getValue().getWidth(), 3);
	}

	@Test
	public void testApplyStyle_whenPropertiesAreEmptyAndStyleIsPartiallyDefined() {
//		UIProperties uiProperties = new UIProperties(border, backgroundColor, padding, shape);
//		UIStyle uiStyle = new UIStyle().
//				withBackgroundColor(newColorFromAwt(YELLOW));
//		UIProperties propertiesWithStyleApplied = styleManager.applyStyles(uiProperties, asSet(uiStyle));
//
//		assertEquals(propertiesWithStyleApplied.getBackgroundColor(), newColorFromAwt(YELLOW));
//		assertEquals(propertiesWithStyleApplied.getBorder(), noBorder());
	}

	@Test
	public void testApplyStyle_whenPropertiesAreFullyPopulatedAndStyleIsPartiallyDefined() {
//		UIProperties uiProperties = new UIProperties(border, backgroundColor, padding, shape);
//		uiProperties.setBackgroundColor(newColorFromAwt(RED));
//		uiProperties.setBorder(newBorder(newColorFromAwt(YELLOW), 4));
//
//		UIStyle uiStyle = new UIStyle().
//				withBackgroundColor(newColorFromAwt(BLACK));
//		UIProperties propertiesWithStyleApplied = styleManager.applyStyles(uiProperties, asSet(uiStyle));
//
//		assertEquals(propertiesWithStyleApplied.getBackgroundColor(), newColorFromAwt(BLACK));
//		assertEquals(propertiesWithStyleApplied.getBorder().getValue().getColor(), newColorFromAwt(YELLOW));
//		assertEquals(propertiesWithStyleApplied.getBorder().getValue().getWidth(), 4);
	}


	@Test
	public void testApplyStyle_whenPropertiesArePartiallyPopulatedAndStyleIsPartiallyDefined() {
//		UIProperties uiProperties = new UIProperties(border, backgroundColor, padding, shape);
//		uiProperties.setBackgroundColor(newColorFromAwt(RED));
//
//		UIStyle uiStyle = new UIStyle().
//				withBorder(newBorder(newColorFromAwt(BLACK), 2));
//		UIProperties propertiesWithStyleApplied = styleManager.applyStyles(uiProperties, asSet(uiStyle));
//
//		assertEquals(propertiesWithStyleApplied.getBackgroundColor(), newColorFromAwt(RED));
//		assertEquals(propertiesWithStyleApplied.getBorder().getValue().getColor(), newColorFromAwt(BLACK));
//		assertEquals(propertiesWithStyleApplied.getBorder().getValue().getWidth(), 2);
	}

	@Test
	public void testApplyStyle_withManyStyles() {
//		UIProperties uiProperties = new UIProperties(border, backgroundColor, padding, shape);
//
//		UIStyle uiStyle1 = new UIStyle().
//				withBorder(newBorder(newColorFromAwt(BLACK), 2));
//		UIStyle uiStyle2 = new UIStyle().
//				withBackgroundColor(newColorFromAwt(RED));
//		UIProperties propertiesWithStylesApplied = styleManager.applyStyles(uiProperties, asSet(uiStyle1, uiStyle2));
//
//		assertEquals(propertiesWithStylesApplied.getBackgroundColor(), newColorFromAwt(RED));
//		assertEquals(propertiesWithStylesApplied.getBorder().getValue().getColor(), newColorFromAwt(BLACK));
//		assertEquals(propertiesWithStylesApplied.getBorder().getValue().getWidth(), 2);
	}

}
