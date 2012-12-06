package com.mgs.fantasi.ui.wireframe;

public class SimpleStructure<T> implements Structure {
	private final T content;


	public SimpleStructure(T content) {
		this.content = content;
	}

	public T getContent() {
		return content;
	}
}
