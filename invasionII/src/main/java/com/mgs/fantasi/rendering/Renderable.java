package com.mgs.fantasi.rendering;

import com.mgs.fantasi.Structurable;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.rendering.wireframe.Wireframe;

public class Renderable implements Structurable {
    private final Wireframe<Renderable> content;
    private final UIProperties uiProperties;

	public Renderable(Wireframe<Renderable> content, UIProperties uiProperties) {
        this.content = content;
        this.uiProperties = uiProperties;
    }

    public Wireframe<Renderable> getContent() {
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
