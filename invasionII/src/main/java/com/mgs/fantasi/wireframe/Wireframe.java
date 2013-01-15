package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.views.WireframeBuilder;

import java.util.List;

public class Wireframe {
	private final WireframeType type;
	private final UIProperties uiProperties;
	private final String name;
	private final Class<? extends WireframeBuilder> builderClass;
	private final WireframeContent wireframeContent;

	public Wireframe(List<WireframeChildElement> content, WireframeType type, UIProperties uiProperties, String name, Class<? extends WireframeBuilder> builderClass) {
		this.wireframeContent = new WireframeContent(content);
		this.type = type;
		this.uiProperties = uiProperties;
		this.name = name;
		this.builderClass = builderClass;
	}

	public WireframeType getType() {
		return type;
	}

	public WireframeContent getContent() {
		return wireframeContent;
	}

	public String getName() {
		return name;
	}

	public UIProperties getUiProperties() {
		return uiProperties;
	}

	public Class<? extends WireframeBuilder> getBuilderClass() {
		return builderClass;
	}

	public List<WireframeChildElement> getContentElements() {
		return getContent().getParts();
	}
}
