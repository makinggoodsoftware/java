package com.mgs.fantasi.rendering.wireframe;

import com.mgs.fantasi.Structurable;
import com.mgs.fantasi.rendering.ViewPreprocessorImpl;
import com.mgs.fantasi.rendering.structure.SimpleStructure;
import com.mgs.fantasi.rendering.structure.Structure;
import com.mgs.fantasi.rendering.structure.StructureType;

public class RectangleWireframe<T extends Structurable> implements Wireframe<T> {
	private T content;

	public RectangleWireframe<T> withContent(T content) {
		if (content==null) throw new IllegalArgumentException("Content can't be null");
		this.content = content;
		return this;
	}

	@Override
	public Structure<T> build() {
		return new SimpleStructure<T>(content);
	}

	@Override
	public <Z extends Structurable> Wireframe<Z> transform(ViewPreprocessorImpl.WireframeTransformer<T, Z> transformer) {
		RectangleWireframe<Z> emptyRectangle = new RectangleWireframe<Z>();
		if (content == null) return emptyRectangle;
		return emptyRectangle.withContent(transformer.transform(content));
	}

	@Override
	public StructureType getType() {
		return StructureType.SIMPLE;
	}

	public T getContent() {
		return content;
	}
}
