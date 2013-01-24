package com.mgs.fantasi.wireframe;

public class WireframeContentFactory {
	private final WireframeCollocationInfoConnectionManager emptyConnectionManager = new WireframeCollocationInfoConnectionManager(WireframeContentType.EMPTY);
	private final WireframeCollocationInfoConnectionManager rectangleConnectionManager = new WireframeCollocationInfoConnectionManager(WireframeContentType.RECTANGLE);
	private final WireframeCollocationInfoConnectionManager layeredConnectionManager = new WireframeCollocationInfoConnectionManager(WireframeContentType.LAYERS);
	private final WireframeCollocationInfoConnectionManager gridConnectionManager = new WireframeCollocationInfoConnectionManager(WireframeContentType.GRID);

	public WireframeCollocationInfoConnectionManager getEmptyConnectionManager() {
		return emptyConnectionManager;
	}

	public WireframeCollocationInfoConnectionManager getGridConnectionManager() {
		return gridConnectionManager;
	}

	public WireframeCollocationInfoConnectionManager getLayeredConnectionManager() {
		return layeredConnectionManager;
	}

	public WireframeCollocationInfoConnectionManager getRectangleConnectionManager() {
		return rectangleConnectionManager;
	}
}
