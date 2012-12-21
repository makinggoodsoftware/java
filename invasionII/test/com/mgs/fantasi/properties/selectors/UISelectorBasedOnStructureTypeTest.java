package com.mgs.fantasi.properties.selectors;

import com.mgs.fantasi.rendering.wireframe.Wireframe;
import com.mgs.fantasi.views.BaseView;
import com.mgs.fantasi.views.View;
import junit.framework.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class UISelectorBasedOnStructureTypeTest {

	@Test
	public void testAppliesTo() throws Exception {
		UISelectorBasedOnStructureType selector = new UISelectorBasedOnStructureType(MatchingStructureType.class);

		Assert.assertTrue(selector.appliesTo(new MatchingStructureType()));
		Assert.assertFalse(selector.appliesTo(new OtherStructureType()));
		Assert.assertFalse(selector.appliesTo(new ExtendingInterface()));
		Assert.assertFalse(selector.appliesTo(new MatchingStructureTypeParent()));
	}

	private View createViewMock(Class<? extends View> structureType) {
		View view = Mockito.mock(View.class);
		Mockito.when(view.getClass()).thenAnswer(withClass(structureType));
		return view;
	}

	private class MatchingStructureTypeParent extends BaseView {
		@Override
		public Wireframe<View> buildChildViews() {
			return null;
		}

		@Override
		protected BaseView copySpecifics() {
			return null;
		}
	}
	private class MatchingStructureType extends MatchingStructureTypeParent {}
	private class ExtendingInterface extends MatchingStructureType {}
	private class OtherStructureType extends BaseView {
		@Override
		public Wireframe<View> buildChildViews() {
			return null;
		}

		@Override
		protected BaseView copySpecifics() {
			return null;
		}
	}

	private Answer<Object> withClass(final Class<? extends View> matchingStructureTypeClass) {
		return new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				return matchingStructureTypeClass;
			}
		};
	}
}
