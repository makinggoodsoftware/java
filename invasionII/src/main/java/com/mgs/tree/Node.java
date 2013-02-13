package com.mgs.tree;

public interface Node<T> {
	T getValue();

	Object getTag(String name);

	void setTag(String name, Object value);
}
