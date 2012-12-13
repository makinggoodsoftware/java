package com.mgs.fantasi.ui.wireframe;

public interface StructureTransformer<T extends Structurable, Z extends Structurable> {
	Z transform (T view);
}
