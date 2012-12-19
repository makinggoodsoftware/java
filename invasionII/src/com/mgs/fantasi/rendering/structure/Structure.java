package com.mgs.fantasi.rendering.structure;

import com.mgs.fantasi.Structurable;

public interface Structure<T extends Structurable> {

	StructureType getType();
}
