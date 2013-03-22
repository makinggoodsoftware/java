package com.mgs.fantasi.structure;

import com.mgs.fantasi.wireframe.Wireframe;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.mgs.fantasi.aux.StructuresForTesting.simpleStructure;
import static com.mgs.fantasi.aux.TestConstants.UNIT_TEST;
import static com.mgs.fantasi.structure.treeAux.WireframeLayoutType.EMPTY;
import static org.testng.Assert.assertEquals;

public class StructuresTest {
	@Mock
	private Wireframe wireframe;

	@BeforeMethod(groups = UNIT_TEST)
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test(groups = UNIT_TEST)
	public void testSimpleStructure() {
		Structure simpleStructure = simpleStructure(wireframe);

		assertEquals(simpleStructure.getChildren().size(), 0);
		assertEquals(simpleStructure.getRoot().getValue(), wireframe);
		assertEquals(simpleStructure.getRoot().getType(), EMPTY);
	}

}
