package com.mgs.fantasi.structure;

import com.mgs.fantasi.structure.structureBuilder.Layout.StructureLayout;
import com.mgs.fantasi.structure.treeAux.WireframeLayoutType;
import com.mgs.fantasi.structure.treeAux.WireframeNode;
import com.mgs.fantasi.wireframe.Wireframe;

public class Structures {
	public static Structure gridStructure(Wireframe wireframe, String name, Class<? extends StructureLayout> builderClass) {
		WireframeNode root = new WireframeNode(wireframe, name, builderClass, WireframeLayoutType.GRID);
		return new Structure(root);
	}

	public static Structure emptyStructure(Wireframe wireframe, String name, Class<? extends StructureLayout> builderClass) {
		WireframeNode root = new WireframeNode(wireframe, name, builderClass, WireframeLayoutType.EMPTY);
		return new Structure(root);
	}

	public static Structure layeredStructure(Wireframe wireframe, String name, Class<? extends StructureLayout> builderClass) {
		WireframeNode root = new WireframeNode(wireframe, name, builderClass, WireframeLayoutType.LAYERS);
		return new Structure(root);
	}

	public static Structure simpleStructure(Wireframe wireframe, String name, Class<? extends StructureLayout> builderClass) {
		WireframeNode root = new WireframeNode(wireframe, name, builderClass, WireframeLayoutType.SIMPLE);
		return new Structure(root);
	}
}
