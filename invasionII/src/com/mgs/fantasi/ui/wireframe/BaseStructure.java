package com.mgs.fantasi.ui.wireframe;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class BaseStructure<T extends Structurable> implements Structure<T>{
	@Override
	public <Z extends Structurable> Structure<Z> transformInto(StructureTransformer<T, Z> structureTransformer, Structure<Z> into) {
		throw new NotImplementedException();
	}


}
