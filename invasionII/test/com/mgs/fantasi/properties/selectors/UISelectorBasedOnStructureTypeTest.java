package com.mgs.fantasi.properties.selectors;

import com.mgs.fantasi.rendering.Renderable;
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

		Assert.assertTrue(selector.appliesTo(createWireframeMock(MatchingStructureType.class)));
		Assert.assertFalse(selector.appliesTo(createWireframeMock(OtherStructureType.class)));
		Assert.assertFalse(selector.appliesTo(createWireframeMock(ExtendingInterface.class)));
		Assert.assertFalse(selector.appliesTo(createWireframeMock(MatchingStructureTypeParent.class)));
	}

	private Renderable createWireframeMock(Class<? extends View> structureType) {
		Renderable renderable = Mockito.mock(Renderable.class);
		Mockito.when(renderable.getView()).thenAnswer(withClass(structureType));
		return renderable;
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
