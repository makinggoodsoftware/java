package com.mgs.fantasi.wireframe;

import java.util.List;

public class Branch<T> {
	private final List<WireframeChildElement<T>> content;
	private final WireframeContentType type;

	public Branch(List<WireframeChildElement<T>> content, WireframeContentType type) {
		this.content = content;
		this.type = type;
	}

	public List<WireframeChildElement<T>> getParts() {
		return content;
	}

	public WireframeContentType getType() {
		return type;
	}
}