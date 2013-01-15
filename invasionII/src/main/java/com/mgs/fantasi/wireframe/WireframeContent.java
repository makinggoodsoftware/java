package com.mgs.fantasi.wireframe;

import java.util.List;

public class WireframeContent {
	final List<WireframeChildElement> content;

	public WireframeContent(List<WireframeChildElement> content) {
		this.content = content;
	}

	public boolean isEmpty() {
		return content.size() == 0;
	}

	public List<WireframeChildElement> getParts() {
		return content;
	}
}