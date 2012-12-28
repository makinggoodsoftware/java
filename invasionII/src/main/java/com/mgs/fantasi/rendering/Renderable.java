package com.mgs.fantasi.rendering;

import com.mgs.fantasi.Structurable;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.rendering.structure.Structure;

public class Renderable implements Structurable {
	private final Structure<Renderable> content;
	private final UIProperties uiProperties;

	public Renderable(Structure<Renderable> content, UIProperties uiProperties) {
		this.uiProperties = uiProperties;
		this.content = content;
	}

    public Structure<Renderable> getContent() {
		return content;
	}

    @Override
	public String toString() {
		return "Renderable{" +
				", uiProperties=" + uiProperties +
				", content=" + content +
				'}';
	}

	public UIProperties getUIProperties() {
		return uiProperties;
	}
}
