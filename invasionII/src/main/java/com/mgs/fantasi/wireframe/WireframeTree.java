package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.views.WireframeTreeBuilder;

import java.util.List;

public class WireframeTree {
	private final Wireframe wireframe;
	private final WireframeContent wireframeContent;

	public WireframeTree(Wireframe wireframe, WireframeContent content) {
		this.wireframeContent = content;
		this.wireframe = wireframe;
	}

	public WireframeContentType getContentType() {
		return wireframeContent.getType();
	}

	public WireframeContent getContent() {
		return wireframeContent;
	}

	public String getName() {
		return wireframe.getName();
	}

	public UIProperties getUiProperties() {
		return wireframe.getUiProperties();
	}

	public Class<? extends WireframeTreeBuilder> getBuilderClass() {
		return wireframe.getBuilderClass();
	}

	public List<WireframeChildElement> getContentElements() {
		return getContent().getParts();
	}

	public boolean isEmpty() {
		return wireframeContent.isEmpty();
	}
}
