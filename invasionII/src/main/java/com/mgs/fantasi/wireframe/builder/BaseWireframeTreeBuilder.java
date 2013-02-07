package com.mgs.fantasi.wireframe.builder;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.properties.UIProperty;
import com.mgs.fantasi.properties.data.Padding;
import com.mgs.fantasi.properties.data.measurements.Measurement;

import static com.mgs.fantasi.properties.UIPropertiesBuilderFactory.rectangularEmpty;

public abstract class BaseWireframeTreeBuilder<T extends BaseWireframeTreeBuilder> implements WireframeTreeBuilder {
	private final UIPropertiesBuilder uiPropertiesBuilder = rectangularEmpty();
	private String name = "";

	@SuppressWarnings(value = "unchecked")
	public T withPadding(UIProperty<Padding> padding) {
		getUiPropertiesBuilder().withPadding(padding);
		return (T) this;
	}

	@SuppressWarnings(value = "unchecked")
	public T withName(String name) {
		this.name = name;
		return (T) this;
	}

	@SuppressWarnings(value = "unchecked")
	public T withMeasurement(UIProperty<Measurement> measurement) {
		getUiPropertiesBuilder().withMeasurement(measurement);
		return (T) this;
	}

	@Override
	public UIPropertiesBuilder getUiPropertiesBuilder() {
		return uiPropertiesBuilder;
	}

	@Override
	public String getName() {
		return name;
	}
}
