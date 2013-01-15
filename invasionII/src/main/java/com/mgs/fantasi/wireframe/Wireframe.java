package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.views.WireframeBuilder;

import java.util.List;

public class Wireframe {
	private final List<Placeholder> placeholders;
	private final WireframeType type;
	private final UIProperties uiProperties;
	private final String name;
	private final Class<? extends WireframeBuilder> builderClass;

	public Wireframe(List<Placeholder> placeholders, WireframeType type, UIProperties uiProperties, String name, Class<? extends WireframeBuilder> builderClass) {
		if (uiProperties == null) throw new RuntimeException("WTF!");
		this.placeholders = placeholders;
		this.type = type;
		this.uiProperties = uiProperties;
		this.name = name;
		this.builderClass = builderClass;
	}

	public WireframeType getType() {
		return type;
	}

	public boolean isEmpty() {
		return placeholders.size() == 0;
	}

	public List<Placeholder> getContent() {
		return placeholders;
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
