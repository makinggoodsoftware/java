package com.mgs.fantasi.ui.profile;

import com.mgs.fantasi.ui.wireframe.Wireframe;
import com.mgs.fantasi.ui.selectors.UISelector;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class UIProfileTest {
	public static final Wireframe WIREFRAME_NOT_IN_CONTEXT = Mockito.mock(Wireframe.class);
	private UIProfile uiProfile;
	private Wireframe wireframe;
	private UIStyle uiStyle1;
	private List<UIStyle> threeUiStyles;
	private UIStyle uiStyle2;
	private UIStyle uiStyle3;

	@Before
	public void setup(){
		uiProfile = new UIProfile();
		wireframe = Mockito.mock(Wireframe.class);
		uiStyle1 = Mockito.mock(UIStyle.class);
		uiStyle2 = Mockito.mock(UIStyle.class);
		uiStyle3 = Mockito.mock(UIStyle.class);
		threeUiStyles = new ArrayList<UIStyle>(Arrays.asList(
			uiStyle1, uiStyle2, uiStyle3
		));
	}
	
	@Test
	public void testFindStylesFor_noStylesAdded() throws Exception {
		Set<UIStyle> styles = uiProfile.findStylesFor(WIREFRAME_NOT_IN_CONTEXT);
		Assert.assertEquals(0, styles.size());
	}

	@Test
	public void testFindStylesFor_oneStyleButNotMatching() throws Exception {
		GivenSetup.onlyOneAndNotMatchingStyle(uiProfile, wireframe, uiStyle1);

		Set<UIStyle> styles = uiProfile.findStylesFor(wireframe);

		Assert.assertEquals(0, styles.size());
	}


	@Test
	public void testFindStylesFor_oneMatchingStyle() throws Exception {
		GivenSetup.onlyOneMatchingStyle(uiProfile, wireframe, uiStyle1);

		Set<UIStyle> styles = uiProfile.findStylesFor(wireframe);

		Assert.assertEquals(1, styles.size());
		Assert.assertEquals(uiStyle1, styles.iterator().next());
	}

	@Test
	public void testFindStylesFor_manyStylesButNoneMatching() throws Exception {
		GivenSetup.manyStylesButNoneMatching(uiProfile, wireframe, threeUiStyles);

		Set<UIStyle> styles = uiProfile.findStylesFor(wireframe);

		Assert.assertEquals(0, styles.size());
	}

	@Test
	public void testFindStylesFor_manyStylesOneMatching() throws Exception {
		GivenSetup.manyStylesManyMatching(uiProfile, wireframe, threeUiStyles, uiStyle1);

		Set<UIStyle> styles = uiProfile.findStylesFor(wireframe);

		Assert.assertEquals(1, styles.size());
		Assert.assertTrue(styles.contains(uiStyle1));
		Assert.assertFalse(styles.contains(uiStyle2));
		Assert.assertFalse(styles.contains(uiStyle3));
	}

	@Test
	public void testFindStylesFor_manyStylesManyMatching() throws Exception {
		GivenSetup.manyStylesManyMatching(uiProfile, wireframe, threeUiStyles, uiStyle1, uiStyle2);

		Set<UIStyle> styles = uiProfile.findStylesFor(wireframe);

		Assert.assertEquals(2, styles.size());
		Assert.assertTrue(styles.contains(uiStyle1));
		Assert.assertTrue(styles.contains(uiStyle2));
		Assert.assertFalse(styles.contains(uiStyle3));
	}



	private static class GivenSetup {
		private static final boolean STYLE_MATCHES = true;
		private static final boolean STYLE_DOESNT_MATCH = false;

		public static void onlyOneMatchingStyle(UIProfile uiProfile, Wireframe wireframe, UIStyle uiStyle) {
			addStyleExpectation(uiProfile, wireframe, uiStyle, STYLE_MATCHES);
		}

		public static void onlyOneAndNotMatchingStyle(UIProfile uiProfile, Wireframe wireframe, UIStyle uiStyle) {
			addStyleExpectation(uiProfile, wireframe, uiStyle, STYLE_DOESNT_MATCH);
		}

		public static void manyStylesButNoneMatching(UIProfile uiProfile, Wireframe wireframe, List<UIStyle> uiStyles) {
			for (UIStyle uiStyle : uiStyles) {
				addStyleExpectation(uiProfile, wireframe, uiStyle, STYLE_DOESNT_MATCH);
			}
		}

		public static void manyStylesManyMatching(UIProfile uiProfile, Wireframe wireframe, List<UIStyle> uiStyles, UIStyle... matchingStyles) {
			for (UIStyle uiStyle : uiStyles) {
				addStyleExpectation(uiProfile, wireframe, uiStyle, anyAreEqual (matchingStyles, uiStyle));
			}
		}

		private static boolean anyAreEqual(UIStyle[] listTocompareTo, UIStyle uiStyle) {
			for (UIStyle toCompareTo : listTocompareTo) {
				if (uiStyle.equals(toCompareTo)) return true;
			}
			return false;
		}

		private static void addStyleExpectation(UIProfile uiProfile, Wireframe wireframe, UIStyle uiStyle, boolean value) {
			UISelector byClassSelectorMock = Mockito.mock(UISelector.class);
			uiProfile.addStyle(byClassSelectorMock, uiStyle);
			Mockito.when(byClassSelectorMock.appliesTo(wireframe)).thenReturn(value);
		}
	}
}
