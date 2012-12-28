package com.mgs.fantasi.rendering.wireframe;

import com.mgs.fantasi.Structurable;
import com.mgs.fantasi.rendering.ViewPreprocessorImpl;
import com.mgs.fantasi.rendering.structure.StructureType;

public class EmptyWireframe<T extends Structurable> implements Wireframe<T>
{
	@Override
	public <Z extends Structurable> Wireframe<Z> transform(ViewPreprocessorImpl.WireframeTransformer<T, Z> tzWireframeTransformer) {
		return new EmptyWireframe<Z>();
	}

	@Override
	public StructureType getType() {
		return StructureType.EMPTY;
	}
}
