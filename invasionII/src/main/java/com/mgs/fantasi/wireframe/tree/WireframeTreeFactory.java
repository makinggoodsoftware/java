package com.mgs.fantasi.wireframe.tree;

import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.tree.builder.WireframeTreeBuilderOld;

public class WireframeTreeFactory {
	public static WireframeTree grid(Wireframe wireframe, String name, Class<? extends WireframeTreeBuilderOld> builderClass) {
		WireframeNode root = new WireframeNode(wireframe, name, builderClass, WireframeLayoutType.GRID);
		return new WireframeTree(root);
	}

	public static WireframeTree empty(Wireframe wireframe, String name, Class<? extends WireframeTreeBuilderOld> builderClass) {
		WireframeNode root = new WireframeNode(wireframe, name, builderClass, WireframeLayoutType.EMPTY);
		return new WireframeTree(root);
	}

	public static WireframeTree layered(Wireframe wireframe, String name, Class<? extends WireframeTreeBuilderOld> builderClass) {
		WireframeNode root = new WireframeNode(wireframe, name, builderClass, WireframeLayoutType.LAYERS);
		return new WireframeTree(root);
	}

	public static WireframeTree rectangle(Wireframe wireframe, String name, Class<? extends WireframeTreeBuilderOld> builderClass) {
		WireframeNode root = new WireframeNode(wireframe, name, builderClass, WireframeLayoutType.SIMPLE);
		return new WireframeTree(root);
	}
}
