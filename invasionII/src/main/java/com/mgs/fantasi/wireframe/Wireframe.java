package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.wireframeTreeBuilders.WireframeTreeBuilder;

public class Wireframe {
	private final UIProperties uiProperties;
	private final String name;
	private final Class<? extends WireframeTreeBuilder> builderClass;

	public Wireframe(Class<? extends WireframeTreeBuilder> builderClass, String name, UIProperties uiProperties) {
		this.builderClass = builderClass;
		this.name = name;
		if (uiProperties == null) throw new RuntimeException("Bullshit!");
		this.uiProperties = uiProperties;
	}

	public String getName() {
		return name;
	}

	public UIProperties getUiProperties() {
		return uiProperties;
	}

	public Class<? extends WireframeTreeBuilder> getBuilderClass() {
		return builderClass;
	}
}