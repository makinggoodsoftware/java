package com.mgs.fantasi.styles;

import com.mgs.fantasi.properties.UIProperties;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.mgs.fantasi.properties.BorderFactory.newBorder;
import static com.mgs.fantasi.properties.BorderFactory.noBorder;
import static com.mgs.fantasi.properties.ColorFactory.newColorFromAwt;
import static com.mgs.fantasi.properties.ColorFactory.transparent;
import static java.awt.Color.*;
import static junit.framework.Assert.assertEquals;

public class StyleManagerImplTest {
	private StyleManager styleManager;

	@Before
	public void setUp() throws Exception {
		styleManager = new StyleManagerImpl();
	}

	@Test
	public void testApplyStyle_whenPropertiesAndStyleAreCompletelyEmpty(){
		UIProperties uiProperties = new UIProperties();
		styleManager.applyStyles(uiProperties, asSet(new UIStyle()));

		assertEquals(uiProperties.getBackgroundColor(), transparent());
		assertEquals(uiProperties.getBorder(), noBorder());
	}

	@Test
	public void testApplyStyle_whenPropertiesAreEmptyAndStyleIsFullyDefined(){
		UIProperties uiProperties = new UIProperties();
		UIStyle uiStyle = new UIStyle().
				withBackgroundColor(newColorFromAwt(YELLOW)).
				withBorder(newBorder(newColorFromAwt(RED), 3));
		UIProperties propertiesWithStyleApplied = styleManager.applyStyles(uiProperties, asSet(uiStyle));

		assertEquals(propertiesWithStyleApplied.getBackgroundColor(), newColorFromAwt(YELLOW));
		assertEquals(propertiesWithStyleApplied.getBorder().getData().getColor(), newColorFromAwt(RED));
		assertEquals(propertiesWithStyleApplied.getBorder().getData().getWidth(), 3);
	}

	@Test
	public void testApplyStyle_whenPropertiesAreEmptyAndStyleIsPartiallyDefined(){
		UIProperties uiProperties = new UIProperties();
		UIStyle uiStyle = new UIStyle().
				withBackgroundColor(newColorFromAwt(YELLOW));
		UIProperties propertiesWithStyleApplied = styleManager.applyStyles(uiProperties, asSet(uiStyle));

		assertEquals(propertiesWithStyleApplied.getBackgroundColor(), newColorFromAwt(YELLOW));
		assertEquals(propertiesWithStyleApplied.getBorder(), noBorder());
	}

	@Test
	public void testApplyStyle_whenPropertiesAreFullyPopulatedAndStyleIsPartiallyDefined (){
		UIProperties uiProperties = new UIProperties();
		uiProperties.setBackgroundColor(newColorFromAwt(RED));
		uiProperties.setBorder(newBorder(newColorFromAwt(YELLOW), 4));

		UIStyle uiStyle = new UIStyle().
				withBackgroundColor(newColorFromAwt(BLACK));
		UIProperties propertiesWithStyleApplied = styleManager.applyStyles(uiProperties, asSet(uiStyle));

		assertEquals(propertiesWithStyleApplied.getBackgroundColor(), newColorFromAwt(BLACK));
		assertEquals(propertiesWithStyleApplied.getBorder().getData().getColor(), newColorFromAwt(YELLOW));
		assertEquals(propertiesWithStyleApplied.getBorder().getData().getWidth(), 4);
	}


	@Test
	public void testApplyStyle_whenPropertiesArePartiallyPopulatedAndStyleIsPartiallyDefined (){
		UIProperties uiProperties = new UIProperties();
		uiProperties.setBackgroundColor(newColorFromAwt(RED));

		UIStyle uiStyle = new UIStyle().
				withBorder(newBorder(newColorFromAwt(BLACK), 2));
		UIProperties propertiesWithStyleApplied = styleManager.applyStyles(uiProperties, asSet(uiStyle));

		assertEquals(propertiesWithStyleApplied.getBackgroundColor(), newColorFromAwt(RED));
		assertEquals(propertiesWithStyleApplied.getBorder().getData().getColor(), newColorFromAwt(BLACK));
		assertEquals(propertiesWithStyleApplied.getBorder().getData().getWidth(), 2);
	}

	@Test
	public void testApplyStyle_withManyStyles (){
		UIProperties uiProperties = new UIProperties();

		UIStyle uiStyle1 = new UIStyle().
				withBorder(newBorder(newColorFromAwt(BLACK), 2));
		UIStyle uiStyle2 = new UIStyle().
				withBackgroundColor(newColorFromAwt(RED));
		UIProperties propertiesWithStylesApplied = styleManager.applyStyles(uiProperties, asSet(uiStyle1, uiStyle2));

		assertEquals(propertiesWithStylesApplied.getBackgroundColor(), newColorFromAwt(RED));
		assertEquals(propertiesWithStylesApplied.getBorder().getData().getColor(), newColorFromAwt(BLACK));
		assertEquals(propertiesWithStylesApplied.getBorder().getData().getWidth(), 2);
	}

	private Set<UIStyle> asSet(UIStyle... styles) {
		Set<UIStyle> asSet = new HashSet<UIStyle>();
		for (UIStyle style : styles) {
			asSet.add(style);
		}
		return asSet;
	}
}
