package com.mgs.fantasi.wireframe;

import com.mgs.tree.ConnectionManager;

public class WireframeContainerFactory {
	private static ConnectionManager<Wireframe, CollocationInfo> gridConnectionManager = new WireframeCollocationInfoConnectionManager(WireframeContentType.GRID);
	private static ConnectionManager<Wireframe, CollocationInfo> emptyConnectionManager = new WireframeCollocationInfoConnectionManager(WireframeContentType.EMPTY);
	private static ConnectionManager<Wireframe, CollocationInfo> layerConnectionManager = new WireframeCollocationInfoConnectionManager(WireframeContentType.LAYERS);
	private static ConnectionManager<Wireframe, CollocationInfo> rectangleConnectionManager = new WireframeCollocationInfoConnectionManager(WireframeContentType.RECTANGLE);

	public static WireframeContainer grid(Wireframe wireframe) {
		return new WireframeContainer(wireframe, connectionManagerFor(WireframeContentType.GRID));
	}

	public static WireframeContainer empty(Wireframe wireframe) {
		return new WireframeContainer(wireframe, connectionManagerFor(WireframeContentType.EMPTY));
	}

	public static WireframeContainer layered(Wireframe wireframe) {
		return new WireframeContainer(wireframe, connectionManagerFor(WireframeContentType.LAYERS));
	}

	public static WireframeContainer rectangle(Wireframe wireframe) {
		return new WireframeContainer(wireframe, connectionManagerFor(WireframeContentType.RECTANGLE));
	}

	private static ConnectionManager<Wireframe, CollocationInfo> connectionManagerFor(WireframeContentType type) {
		switch (type) {
			case GRID:
				return gridConnectionManager;
			case EMPTY:
				return emptyConnectionManager;
			case LAYERS:
				return layerConnectionManager;
			case RECTANGLE:
				return rectangleConnectionManager;
		}
		throw new RuntimeException("Unexpected code point reached");
	}
}
