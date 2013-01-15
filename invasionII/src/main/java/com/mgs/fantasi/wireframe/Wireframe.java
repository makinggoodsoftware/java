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

	public Wireframe(List<Placeholder> content, WireframeType type, UIProperties uiProperties, String name, Class<? extends WireframeBuilder> builderClass) {
		this.wireframeContent = new WireframeContent(content);
		this.type = type;
		this.uiProperties = uiProperties;
		this.name = name;
		this.builderClass = builderClass;
	}

	public WireframeType getType() {
		return type;
	}

	public boolean isEmpty() {
		return wireframeContent.isEmpty();
	}

	public List<Placeholder> getContent() {
		return wireframeContent.getContent();
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
}
