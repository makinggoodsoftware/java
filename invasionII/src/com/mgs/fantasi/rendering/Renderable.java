package com.mgs.fantasi.rendering;

import com.mgs.fantasi.Structurable;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;
import com.mgs.fantasi.rendering.structure.Structure;

public class Renderable implements Structurable {
	private final Structure<Renderable> content;
	private final UIProperties uiProperties;

	public Renderable(Structure<Renderable> content, UIProperties uiProperties) {
		this.uiProperties = uiProperties;
		this.content = content;
	}

	public PolygonPointsIterator getShape() {
		return uiProperties.getShape();
	}

	public Structure<Renderable> getContent() {
		return content;
	}

	public Margin getMargin() {
		return uiProperties.getMargin();
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
