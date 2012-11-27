package com.mgs.fantasi.ui.selectors;

import com.mgs.fantasi.ui.wireframe.Wireframe;
import com.mgs.fantasi.structures.StructureBuilder;
import org.junit.Assert;
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

	private Wireframe createWireframeMock(Class<? extends StructureBuilder> structureType) {
		Wireframe wireframe = Mockito.mock(Wireframe.class);
		Mockito.when(wireframe.getStructureType()).thenAnswer(withClass(structureType));
		return wireframe;
	}

	private interface MatchingStructureTypeParent extends StructureBuilder {}
	private interface MatchingStructureType extends MatchingStructureTypeParent {}
	private interface ExtendingInterface extends MatchingStructureType {}
	private interface OtherStructureType extends StructureBuilder {}

	private Answer<Object> withClass(final Class<? extends StructureBuilder> matchingStructureTypeClass) {
		return new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				return matchingStructureTypeClass;
			}
		};
	}
}
