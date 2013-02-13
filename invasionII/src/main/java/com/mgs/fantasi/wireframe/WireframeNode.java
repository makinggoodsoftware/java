package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.wireframe.builder.WireframeTreeBuilder;
import com.mgs.tree.TemplateNode;

public class WireframeNode extends TemplateNode<Wireframe> {
	private static final String BUILDER = "builder";
	private static final String NAME = "name";

	public WireframeNode(Wireframe value, String name, Class<? extends WireframeTreeBuilder> builderClass) {
		super(value);
		setTag(NAME, name);
		setTag(BUILDER, builderClass);
	}

	public String getName() {
		return (String) getTag(NAME);
	}

	public Class<? extends WireframeTreeBuilder> getBuilder() {
		return (Class<? extends WireframeTreeBuilder>) getTag(BUILDER);
	}
}
