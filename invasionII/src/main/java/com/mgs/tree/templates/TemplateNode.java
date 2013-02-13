package com.mgs.tree.templates;

import com.mgs.tree.Node;

import java.util.HashMap;
import java.util.Map;

public abstract class TemplateNode<T> implements Node<T> {
	private final T value;
	private final Map<String, Object> tags = new HashMap<String, Object>();

	public TemplateNode(T value) {
		this.value = value;
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public Object getTag(String name) {
		return tags.get(name);
	}

	@Override
	public void setTag(String name, Object value) {
		tags.put(name, value);
	}
}
