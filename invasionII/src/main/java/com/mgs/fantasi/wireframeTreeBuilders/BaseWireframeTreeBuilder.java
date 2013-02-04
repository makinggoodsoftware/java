package com.mgs.fantasi.wireframeTreeBuilders;

import com.mgs.fantasi.properties.EmptyRectangularUIPropertiesBuilder;
import com.mgs.fantasi.properties.Padding;
import com.mgs.fantasi.properties.UIProperty;
import com.mgs.fantasi.properties.measurements.Measurement;

public abstract class BaseWireframeTreeBuilder<T extends BaseWireframeTreeBuilder> implements WireframeTreeBuilder {
	private final EmptyRectangularUIPropertiesBuilder uiPropertiesBuilder = new EmptyRectangularUIPropertiesBuilder();
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
	public EmptyRectangularUIPropertiesBuilder getUiPropertiesBuilder() {
		return uiPropertiesBuilder;
	}

	@Override
	public String getName() {
		return name;
	}
}
