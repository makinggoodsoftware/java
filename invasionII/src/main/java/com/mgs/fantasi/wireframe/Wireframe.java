package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.views.WireframeBuilder;

import java.util.List;

public class Wireframe {
	private final UIProperties uiProperties;
	private final String name;
	private final Class<? extends WireframeBuilder> builderClass;
	private final WireframeContent wireframeContent;

	public Wireframe(WireframeContent content, UIProperties uiProperties, String name, Class<? extends WireframeBuilder> builderClass) {
		this.wireframeContent = content;
		this.uiProperties = uiProperties;
		this.name = name;
		this.builderClass = builderClass;
	}

	public WireframeContentType getContentType() {
		return wireframeContent.getType();
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
