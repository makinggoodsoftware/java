package com.mgs.fantasi.wireframe;

import java.util.Map;
import java.util.Set;

public class Tree<T, Z> {
	private final T content;
	private final Branch<T, Z> branch;

	public Tree(T content, Branch<T, Z> branch) {
		this.branch = branch;
		this.content = content;
	}

	public Branch<T, Z> getChildren() {
		return branch;
	}

	public T getContent() {
		return content;
	}

	public Set<Map.Entry<Z, Tree<T, Z>>> getContentElements() {
		return getChildren().getParts();
	}

	public Branch<T, Z> getBranch() {
		return branch;
	}
}
