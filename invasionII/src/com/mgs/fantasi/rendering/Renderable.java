package com.mgs.fantasi.rendering;

import com.mgs.fantasi.Structurable;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;
import com.mgs.fantasi.rendering.wireframe.Wireframe;
import com.mgs.fantasi.views.View;

public class Renderable implements Structurable {
	private final Class<? extends View> view;
	private final Wireframe<Renderable> content;
	private final UIProperties uiProperties;

	public Renderable(Class<? extends View> view, Wireframe<Renderable> content, UIProperties uiProperties) {
		this.uiProperties = uiProperties;
		this.view = view;
		this.content = content;
	}

	public Class<? extends View> getView() {
		return view;
	}

	public PolygonPointsIterator getShape() {
		return uiProperties.getShape();
	}

	public Wireframe<Renderable> getContent() {
		return content;
	}

	public Margin getMargin() {
		return uiProperties.getMargin();
	}

	@Override
	public String toString() {
		return "Renderable{" +
				"view=" + view +
				", uiProperties=" + uiProperties +
				", content=" + content +
				'}';
	}

	public String getName() {
		return uiProperties.getName();
	}
}
