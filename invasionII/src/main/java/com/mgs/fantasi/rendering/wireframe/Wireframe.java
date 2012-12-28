package com.mgs.fantasi.rendering.wireframe;

import com.mgs.fantasi.Structurable;
import com.mgs.fantasi.rendering.ViewPreprocessorImpl;

public interface Wireframe<T extends Structurable> {
	<Z extends Structurable> Wireframe<Z> transform(ViewPreprocessorImpl.WireframeTransformer<T, Z> wireframeTransformer);

	WireframeType getType();
}
