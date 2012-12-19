package com.mgs.fantasi.rendering.wireframe;

import com.mgs.fantasi.Structurable;
import com.mgs.fantasi.rendering.MyViewPreprocessor;
import com.mgs.fantasi.rendering.structure.EmptyStructure;
import com.mgs.fantasi.rendering.structure.Structure;

public class EmptyWireframe<T extends Structurable> implements Wireframe<T>
{
	@Override
	public Structure<T> build() {
		return new EmptyStructure<T>();
	}

	@Override
	public <Z extends Structurable> Wireframe<Z> transform(MyViewPreprocessor.WireframeTransformer<T, Z> tzWireframeTransformer) {
		return new EmptyWireframe<Z>();
	}
}
