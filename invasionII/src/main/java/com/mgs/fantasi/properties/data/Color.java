package com.mgs.fantasi.properties.data;

public class Color implements UIPropertyData {
	private final java.awt.Color colorAsAwt;

	public Color(java.awt.Color colorAsAwt) {
		this.colorAsAwt = colorAsAwt;
	}

	public java.awt.Color getColorAsAwt() {
		return colorAsAwt;
	}
}
