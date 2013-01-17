package com.mgs.fantasi.wireframe;

import java.util.List;

public class WireframeContent {
	private final List<WireframeChildElement> content;
	private final WireframeContentType type;

	public WireframeContent(List<WireframeChildElement> content, WireframeContentType type) {
		this.content = content;
		this.type = type;
	}

	public boolean isEmpty() {
		return content.size() == 0;
	}

	public List<WireframeChildElement> getParts() {
		return content;
	}

	public WireframeContentType getType() {
		return type;
	}
}