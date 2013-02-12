package com.mgs.tree;

public class Node<T> {
	private final T value;

	public Node(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}
}
