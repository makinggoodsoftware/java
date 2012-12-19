package com.mgs.fantasi.rendering.wireframe;

import com.mgs.fantasi.Structurable;
import com.mgs.fantasi.rendering.MyViewPreprocessor;
import com.mgs.fantasi.rendering.structure.Structure;

public interface Wireframe<T extends Structurable> {
	Structure<T> build();

	<Z extends Structurable> Wireframe<Z> transform(MyViewPreprocessor.WireframeTransformer<T, Z> wireframeTransformer);
}
