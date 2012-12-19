package com.mgs.fantasi.properties.selectors;

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

		Assert.assertTrue(selector.appliesTo(createViewMock(MatchingStructureType.class)));
		Assert.assertFalse(selector.appliesTo(createViewMock(OtherStructureType.class)));
		Assert.assertFalse(selector.appliesTo(createViewMock(ExtendingInterface.class)));
		Assert.assertFalse(selector.appliesTo(createViewMock(MatchingStructureTypeParent.class)));
	}

	private View createViewMock(Class<? extends View> structureType) {
		View view = Mockito.mock(View.class);
		Mockito.when(view.getClass()).thenAnswer(withClass(structureType));
		return view;
	}

	private interface MatchingStructureTypeParent extends View {}
	private interface MatchingStructureType extends MatchingStructureTypeParent {}
	private interface ExtendingInterface extends MatchingStructureType {}
	private interface OtherStructureType extends View {}

	private Answer<Object> withClass(final Class<? extends View> matchingStructureTypeClass) {
		return new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				return matchingStructureTypeClass;
			}
		};
	}
}
