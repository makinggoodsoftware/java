package com.mgs.fantasi.wireframe;

public class DelegateWireframe<T> implements Wireframe<T> {
	private T content;

	@Override
	public WireframeType getType() {
		return WireframeType.DELEGATE;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	public DelegateWireframe<T> withContent(T content) {
		this.content = content;
		return this;
	}

	public T getContent() {
		return content;
	}
}
