package com.mgs.tree.templates;

import com.mgs.tree.Node;
import com.mgs.tree.TreeWithConnectionInfo;
import com.mgs.tree.TreeWithConnectionInfoBuilder;

import java.util.HashMap;
import java.util.Map;

public abstract class TemplateTreeWithConnectionInfoBuilder<T, Z, Y extends TreeWithConnectionInfo<T, Z, Y>> implements TreeWithConnectionInfoBuilder<T, Z, Y> {
	private final Node<T> root;
	private Map<Z, TreeWithConnectionInfoBuilder<T, Z, Y>> children = new HashMap<Z, TreeWithConnectionInfoBuilder<T, Z, Y>>();

	protected TemplateTreeWithConnectionInfoBuilder(Node<T> root) {
		this.root = root;
	}


	public abstract Y create(Node<T> node, Map<Z, Y> children);


	@Override
	public final TreeWithConnectionInfoBuilder<T, Z, Y> withChild(TreeWithConnectionInfoBuilder<T, Z, Y> value, Z connectionInfo) {
		this.children.put(connectionInfo, value);
		return this;
	}

	@Override
	public final TreeWithConnectionInfo<T, Z, Y> build() {
		Map<Z, Y> builtChildren = new HashMap<Z, Y>();
		for (Map.Entry<Z, TreeWithConnectionInfoBuilder<T, Z, Y>> child : children.entrySet()) {
			builtChildren.put(child.getKey(), (Y) child.getValue().build());
		}
		return create(root, builtChildren);
	}
}
