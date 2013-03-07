package com.mgs.fantasi.wireframe.tree;

import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.tree.builder.BluePrint;
import com.mgs.tree.templates.TemplateNode;

public class WireframeNode extends TemplateNode<Wireframe> {
	private static final String BUILDER = "builder";
	private static final String NAME = "name";
	private static final String TYPE = "type";

	public WireframeNode(Wireframe value, String name, Class<? extends BluePrint> builderClass, WireframeLayoutType type) {
		super(value);
		setTag(NAME, name);
		setTag(BUILDER, builderClass);
		setTag(TYPE, type);
	}

	public String getName() {
		return (String) getTag(NAME);
	}

	public Class<? extends BluePrint> getBuilder() {
		return (Class<? extends BluePrint>) getTag(BUILDER);
	}

	public WireframeLayoutType getType() {
		return (WireframeLayoutType) getTag(TYPE);
	}

	public PolygonPointsIterator getShape() {
		return getValue().getShape();
	}
}
