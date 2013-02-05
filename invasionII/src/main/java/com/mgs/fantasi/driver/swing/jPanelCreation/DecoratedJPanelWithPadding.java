package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.data.Padding;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.properties.data.measurements.Measurement;
import com.mgs.fantasi.properties.data.measurements.Measurements;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.WireframeContentType;

import javax.swing.*;
import java.awt.*;

import static com.mgs.fantasi.driver.swing.SwingUtils.coordinates;

public class DecoratedJPanelWithPadding implements JPanelBuilder {
	private final JPanelBuilder decoratedPanel;
	private final Padding padding;

	public DecoratedJPanelWithPadding(JPanelBuilder decoratedPanel, Padding padding) {
		this.decoratedPanel = decoratedPanel;
		this.padding = padding;
	}

	@Override
	public JPanel build() {
		JPanel jPanel = decoratedPanel.build();

		JPanel outmostPointer = jPanel;
		if (!padding.isEmpty()) {
			outmostPointer = decorateWithPadding(jPanel, padding);
		}
		return outmostPointer;
	}

	@Override
	public WireframeContentType getContentType() {
		return decoratedPanel.getContentType();
	}

	@Override
	public JPanelBuilder withChild(JPanel child, CollocationInfo collocationInfo) {
		this.decoratedPanel.withChild(child, collocationInfo);
		return this;
	}

	protected final JPanel decorateWithPadding(JPanel nativeElement, Padding padding) {
		JPanel paddingContainer = new JPanel();
		paddingContainer.setOpaque(false);
		paddingContainer.setLayout(new GridBagLayout());
		int top = resolveMeasurement(padding.getTop());
		int right = resolveMeasurement(padding.getRight());
		int bottom = resolveMeasurement(padding.getBottom());
		int left = resolveMeasurement(padding.getLeft());
		paddingContainer.setBorder(javax.swing.BorderFactory.createEmptyBorder(top, right, bottom, left));
		paddingContainer.add(nativeElement, coordinates(0, 0, Fractions.all(), Fractions.all()));
		return paddingContainer;
	}

	private int resolveMeasurement(Measurement measurement) {
		if (measurement instanceof Measurements.SimpleMeasurement) {
			return ((Measurements.SimpleMeasurement) measurement).getIntValue();
		}
		return 0;
	}

}
