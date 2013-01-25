package com.mgs.fantasi.wireframeTreeBuilders;

import com.mgs.fantasi.properties.Padding;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.properties.measurements.Measurement;

public abstract class BaseWireframeTreeBuilder<T extends BaseWireframeTreeBuilder> implements WireframeTreeBuilder {
	private final UIProperties uiProperties = new UIProperties();
	private String name = "";

	@SuppressWarnings(value = "unchecked")
	public T withPadding(Padding padding) {
		getUiProperties().setPadding(padding);
		return (T) this;
	}

	@SuppressWarnings(value = "unchecked")
	public T withName(String name) {
		this.name = name;
		return (T) this;
	}

	@SuppressWarnings(value = "unchecked")
	public T withMeasurement(Measurement measurement) {
		getUiProperties().setMeasurement(measurement);
		return (T) this;
	}

	@Override
	public UIProperties getUiProperties() {
		return uiProperties;
	}

	@Override
	public String getName() {
		return name;
	}
}