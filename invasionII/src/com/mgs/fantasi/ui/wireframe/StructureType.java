package com.mgs.fantasi.ui.wireframe;

public class StructureType {
	public static RectangleWireframe rectangle() {
		return new RectangleWireframe();
	}

	public static <T extends Structurable> GridWireframe<T> grid() {
		return new GridWireframe();
	}
}
