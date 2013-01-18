package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.views.WireframeBuilder;

public class Wireframe {
	final UIProperties uiProperties;
	final String name;
	final Class<? extends WireframeBuilder> builderClass;

	public Wireframe(Class<? extends WireframeBuilder> builderClass, String name, UIProperties uiProperties) {
		this.builderClass = builderClass;
		this.name = name;
		this.uiProperties = uiProperties;
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
}