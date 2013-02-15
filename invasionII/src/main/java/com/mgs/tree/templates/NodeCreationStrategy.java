package com.mgs.tree.templates;

import com.mgs.tree.Node;

public interface NodeCreationStrategy<T, X extends Node<T>> {
	X create(T rootValue);
}
