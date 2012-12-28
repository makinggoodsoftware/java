package com.mgs.fantasi.rendering.wireframe;

import com.mgs.fantasi.Structurable;
import com.mgs.fantasi.rendering.ViewPreprocessorImpl;
import com.mgs.fantasi.rendering.structure.DelegateStructure;
import com.mgs.fantasi.rendering.structure.Structure;
import com.mgs.fantasi.rendering.structure.StructureType;

public class DelegateWireframe<T extends Structurable> implements Wireframe<T> {
	private Wireframe<T> content;

	@Override
	public Structure<T> build() {
		return new DelegateStructure<T>(content.build());
	}

	@Override
	public <Z extends Structurable> Wireframe<Z> transform(ViewPreprocessorImpl.WireframeTransformer<T, Z> transformer) {
		return content.transform(transformer);
	}

	@Override
	public StructureType getType() {
		return StructureType.DELEGATE;
	}

	public DelegateWireframe<T> withContent(Wireframe<T> content) {
		this.content = content;
		return this;
	}

    public Wireframe<T> getContent() {
        return content;
    }
}
