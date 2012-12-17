package com.mgs.fantasi.ui.wireframe;

public interface Structure<T extends Structurable> {
	<Z extends Structurable> Structure<Z> transformInto(StructureTransformer<T, Z> structureTransformer, Structure<Z> into);


}
