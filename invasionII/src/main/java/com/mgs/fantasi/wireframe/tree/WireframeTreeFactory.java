package com.mgs.fantasi.wireframe.tree;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.tree.builder.WireframeTreeBuilder;
import com.mgs.tree.templates.ConnectionManager;

public class WireframeTreeFactory {
	private static ConnectionManager<Wireframe, CollocationInfo> gridConnectionManager = new WireframeCollocationInfoConnectionManager(WireframeChildrenLayout.GRID);
	private static ConnectionManager<Wireframe, CollocationInfo> emptyConnectionManager = new WireframeCollocationInfoConnectionManager(WireframeChildrenLayout.EMPTY);
	private static ConnectionManager<Wireframe, CollocationInfo> layerConnectionManager = new WireframeCollocationInfoConnectionManager(WireframeChildrenLayout.LAYERS);
	private static ConnectionManager<Wireframe, CollocationInfo> rectangleConnectionManager = new WireframeCollocationInfoConnectionManager(WireframeChildrenLayout.SIMPLE);
	public static final String NODE_TAG_NAME = "name";
	public static final String NODE_TAG_BUILDER_NAME = "builderName";

	public static WireframeTree grid(Wireframe wireframe, String name, Class<? extends WireframeTreeBuilder> builderClass) {
		WireframeNode root = new WireframeNode(wireframe, name, builderClass);
		return new WireframeTree(root, connectionManagerFor(WireframeChildrenLayout.GRID));
	}

	public static WireframeTree empty(Wireframe wireframe, String name, Class<? extends WireframeTreeBuilder> builderClass) {
		WireframeNode root = new WireframeNode(wireframe, name, builderClass);
		return new WireframeTree(root, connectionManagerFor(WireframeChildrenLayout.EMPTY));
	}

	public static WireframeTree layered(Wireframe wireframe, String name, Class<? extends WireframeTreeBuilder> builderClass) {
		WireframeNode root = new WireframeNode(wireframe, name, builderClass);
		return new WireframeTree(root, connectionManagerFor(WireframeChildrenLayout.LAYERS));
	}

	public static WireframeTree rectangle(Wireframe wireframe, String name, Class<? extends WireframeTreeBuilder> builderClass) {
		WireframeNode root = new WireframeNode(wireframe, name, builderClass);
		return new WireframeTree(root, connectionManagerFor(WireframeChildrenLayout.SIMPLE));
	}

	private static ConnectionManager<Wireframe, CollocationInfo> connectionManagerFor(WireframeChildrenLayout type) {
		switch (type) {
			case GRID:
				return gridConnectionManager;
			case EMPTY:
				return emptyConnectionManager;
			case LAYERS:
				return layerConnectionManager;
			case SIMPLE:
				return rectangleConnectionManager;
		}
		throw new RuntimeException("Unexpected code point reached");
	}
}
