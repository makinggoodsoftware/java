package com.mgs.fantasi.structure;

import com.mgs.fantasi.aux.StructuresForTesting;
import org.testng.annotations.Test;

import static com.mgs.fantasi.structure.treeAux.WireframeLayoutType.EMPTY;
import static com.mgs.fantasi.wireframe.Wireframes.basicRectangle;
import static org.testng.Assert.assertEquals;

public class StructuresTest {
	@Test
	public void testSimpleStructure() {
		Structure simpleStructure = StructuresForTesting.simpleStructure();

		assertEquals(simpleStructure.getChildren().size(), 0);
		assertEquals(simpleStructure.getRoot().getValue(), basicRectangle());
		assertEquals(simpleStructure.getRoot().getType(), EMPTY);
	}

}
