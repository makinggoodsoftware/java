package com.mgs.fantasi.ui.wireframe;

public class StructureType {
	public static RectangleStructureBuilder rectangle() {
		return new RectangleStructureBuilder();
	}

	public static GridStructureBuilder grid() {
		return new GridStructureBuilder();
	}
}
