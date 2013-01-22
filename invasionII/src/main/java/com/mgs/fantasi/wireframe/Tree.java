package com.mgs.fantasi.wireframe;

import java.util.List;

public class Tree<T> {
	private final T content;
	private final Branch<T> branch;

	public Tree(T content, Branch<T> branch) {
		this.branch = branch;
		this.content = content;
	}

	public WireframeContentType getContentType() {
		return branch.getType();
	}

	public Branch<T> getChildren() {
		return branch;
	}

	public T getContent() {
		return content;
	}

	public List<WireframeChildElement<T>> getContentElements() {
		return getChildren().getParts();
	}

}
