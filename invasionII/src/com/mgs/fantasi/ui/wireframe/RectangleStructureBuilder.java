package com.mgs.fantasi.ui.wireframe;

public class RectangleStructureBuilder<T extends Structurable> implements StructureBuilder<T>{
	private T content;

	public RectangleStructureBuilder<T> withContent(T content) {
		if (content==null) throw new IllegalArgumentException("Content can't be null");
		this.content = content;
		return this;
	}

	@Override
	public Structure<T> build() {
		return new SimpleStructure<T>(content);
	}

	@Override
	public <Z extends Structurable> StructureBuilder<Z> transform(MyRenderer.StructureBuilderTransformer<T, Z> transformer) {
		RectangleStructureBuilder<Z> emptyRectangle = new RectangleStructureBuilder<Z>();
		if (content == null) return emptyRectangle;
		return emptyRectangle.withContent(transformer.transform(content));
	}
}
