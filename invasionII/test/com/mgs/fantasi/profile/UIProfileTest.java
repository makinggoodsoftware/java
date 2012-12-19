package com.mgs.fantasi.profile;

import com.mgs.fantasi.properties.selectors.UISelector;
import com.mgs.fantasi.rendering.Renderable;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class UIProfileTest {
	public static final Renderable RENDERABLE_NOT_IN_CONTEXT = Mockito.mock(Renderable.class);
	private UIProfile uiProfile;
	private Renderable renderable;
	private UIStyle uiStyle1;
	private List<UIStyle> threeUiStyles;
	private UIStyle uiStyle2;
	private UIStyle uiStyle3;

	@Before
	public void setup(){
		uiProfile = new UIProfile();
		renderable = Mockito.mock(Renderable.class);
		uiStyle1 = Mockito.mock(UIStyle.class);
		uiStyle2 = Mockito.mock(UIStyle.class);
		uiStyle3 = Mockito.mock(UIStyle.class);
		threeUiStyles = new ArrayList<UIStyle>(Arrays.asList(
			uiStyle1, uiStyle2, uiStyle3
		));
	}
	
	@Test
	public void testFindStylesFor_noStylesAdded() throws Exception {
		Set<UIStyle> styles = uiProfile.findStylesFor(RENDERABLE_NOT_IN_CONTEXT);
		Assert.assertEquals(0, styles.size());
	}

	@Test
	public void testFindStylesFor_oneStyleButNotMatching() throws Exception {
		GivenSetup.onlyOneAndNotMatchingStyle(uiProfile, renderable, uiStyle1);

		Set<UIStyle> styles = uiProfile.findStylesFor(renderable);

		Assert.assertEquals(0, styles.size());
	}


	@Test
	public void testFindStylesFor_oneMatchingStyle() throws Exception {
		GivenSetup.onlyOneMatchingStyle(uiProfile, renderable, uiStyle1);

		Set<UIStyle> styles = uiProfile.findStylesFor(renderable);

		Assert.assertEquals(1, styles.size());
		Assert.assertEquals(uiStyle1, styles.iterator().next());
	}

	@Test
	public void testFindStylesFor_manyStylesButNoneMatching() throws Exception {
		GivenSetup.manyStylesButNoneMatching(uiProfile, renderable, threeUiStyles);

		Set<UIStyle> styles = uiProfile.findStylesFor(renderable);

		Assert.assertEquals(0, styles.size());
	}

	@Test
	public void testFindStylesFor_manyStylesOneMatching() throws Exception {
		GivenSetup.manyStylesManyMatching(uiProfile, renderable, threeUiStyles, uiStyle1);

		Set<UIStyle> styles = uiProfile.findStylesFor(renderable);

		Assert.assertEquals(1, styles.size());
		Assert.assertTrue(styles.contains(uiStyle1));
		Assert.assertFalse(styles.contains(uiStyle2));
		Assert.assertFalse(styles.contains(uiStyle3));
	}

	@Test
	public void testFindStylesFor_manyStylesManyMatching() throws Exception {
		GivenSetup.manyStylesManyMatching(uiProfile, renderable, threeUiStyles, uiStyle1, uiStyle2);

		Set<UIStyle> styles = uiProfile.findStylesFor(renderable);

		Assert.assertEquals(2, styles.size());
		Assert.assertTrue(styles.contains(uiStyle1));
		Assert.assertTrue(styles.contains(uiStyle2));
		Assert.assertFalse(styles.contains(uiStyle3));
	}



	private static class GivenSetup {
		private static final boolean STYLE_MATCHES = true;
		private static final boolean STYLE_DOESNT_MATCH = false;

		public static void onlyOneMatchingStyle(UIProfile uiProfile, Renderable renderable, UIStyle uiStyle) {
			addStyleExpectation(uiProfile, renderable, uiStyle, STYLE_MATCHES);
		}

		public static void onlyOneAndNotMatchingStyle(UIProfile uiProfile, Renderable renderable, UIStyle uiStyle) {
			addStyleExpectation(uiProfile, renderable, uiStyle, STYLE_DOESNT_MATCH);
		}

		public static void manyStylesButNoneMatching(UIProfile uiProfile, Renderable renderable, List<UIStyle> uiStyles) {
			for (UIStyle uiStyle : uiStyles) {
				addStyleExpectation(uiProfile, renderable, uiStyle, STYLE_DOESNT_MATCH);
			}
		}

		public static void manyStylesManyMatching(UIProfile uiProfile, Renderable renderable, List<UIStyle> uiStyles, UIStyle... matchingStyles) {
			for (UIStyle uiStyle : uiStyles) {
				addStyleExpectation(uiProfile, renderable, uiStyle, anyAreEqual (matchingStyles, uiStyle));
			}
		}

		private static boolean anyAreEqual(UIStyle[] listToCompareTo, UIStyle uiStyle) {
			for (UIStyle toCompareTo : listToCompareTo) {
				if (uiStyle.equals(toCompareTo)) return true;
			}
			return false;
		}

		private static void addStyleExpectation(UIProfile uiProfile, Renderable renderable, UIStyle uiStyle, boolean value) {
			UISelector byClassSelectorMock = Mockito.mock(UISelector.class);
			uiProfile.addStyle(byClassSelectorMock, uiStyle);
			Mockito.when(byClassSelectorMock.appliesTo(renderable)).thenReturn(value);
		}
	}
}
