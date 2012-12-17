package com.mgs.fantasi.ui.wireframe;

public class StructureType {
	public static RectangleStructureBuilder rectangle() {
		return new RectangleStructureBuilder();
	}

	public static <T extends Structurable> GridStructureBuilder<T> grid() {
		return new GridStructureBuilder();
	}
}
