package com.mgs.fantasi.wireframe;

import java.util.List;

public class WireframeContent {
	final List<Placeholder> content;

	public WireframeContent(List<Placeholder> content) {
		this.content = content;
	}

	public boolean isEmpty() {
		return content.size() == 0;
	}

	public List<Placeholder> getContent() {
		return content;
	}
}