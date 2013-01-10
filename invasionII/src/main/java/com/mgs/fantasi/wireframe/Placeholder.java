package com.mgs.fantasi.wireframe;

public class Placeholder<T> {
	private final T content;

	public Placeholder(T content) {
		this.content = content;
	}

	public T getContent() {
		return content;
	}
}
