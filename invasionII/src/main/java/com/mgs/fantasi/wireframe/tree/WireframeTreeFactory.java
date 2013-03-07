package com.mgs.fantasi.wireframe.tree;

import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.tree.builder.BluePrint;

public class WireframeTreeFactory {
	public static Structure grid(Wireframe wireframe, String name, Class<? extends BluePrint> builderClass) {
		WireframeNode root = new WireframeNode(wireframe, name, builderClass, WireframeLayoutType.GRID);
		return new Structure(root);
	}

	public static Structure emptyTree(Wireframe wireframe, String name, Class<? extends BluePrint> builderClass) {
		WireframeNode root = new WireframeNode(wireframe, name, builderClass, WireframeLayoutType.EMPTY);
		return new Structure(root);
	}

	public static Structure layered(Wireframe wireframe, String name, Class<? extends BluePrint> builderClass) {
		WireframeNode root = new WireframeNode(wireframe, name, builderClass, WireframeLayoutType.LAYERS);
		return new Structure(root);
	}

	public static Structure rectangle(Wireframe wireframe, String name, Class<? extends BluePrint> builderClass) {
		WireframeNode root = new WireframeNode(wireframe, name, builderClass, WireframeLayoutType.SIMPLE);
		return new Structure(root);
	}
}
