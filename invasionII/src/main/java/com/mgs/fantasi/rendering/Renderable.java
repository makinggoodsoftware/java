package com.mgs.fantasi.rendering;

import com.mgs.fantasi.Structurable;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.rendering.structure.Structure;
import com.mgs.fantasi.rendering.wireframe.Wireframe;

public class Renderable implements Structurable {
	private final Structure<Renderable> content;
    private final Wireframe<Renderable> wireframeOfRenderables;
    private final UIProperties uiProperties;

	public Renderable(Wireframe<Renderable> wireframeOfRenderables, Structure<Renderable> content, UIProperties uiProperties) {
        this.wireframeOfRenderables = wireframeOfRenderables;
        this.uiProperties = uiProperties;
		this.content = content;
	}

    public Structure<Renderable> getContent() {
		return content;
	}

    public Wireframe<Renderable> getContentAsWireframes() {
        return wireframeOfRenderables;
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
