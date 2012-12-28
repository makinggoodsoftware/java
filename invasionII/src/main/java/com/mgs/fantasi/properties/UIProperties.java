package com.mgs.fantasi.properties;

import com.mgs.fantasi.properties.measurements.Measurement;
import com.mgs.fantasi.properties.polygon.NativeRectanguarShape;
import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;
import org.apache.commons.beanutils.BeanUtils;

import static com.mgs.fantasi.properties.BorderFactory.noBorder;
import static com.mgs.fantasi.properties.ColorFactory.transparent;
import static com.mgs.fantasi.properties.measurements.EmptyMeasurement.emptyMeasurement;

public class UIProperties {
    PolygonPointsIterator shape = new NativeRectanguarShape();
	Padding padding = Padding.zero();
	Measurement measurement = emptyMeasurement();
	private UIPropertyProvider<BorderFactory.Border> border = noBorder();
	private UIPropertyProvider<ColorFactory.Color> backgroundColor = transparent();

	public UIProperties() {
	}

	public void setBorder(UIPropertyProvider<BorderFactory.Border> border) {
		this.border = border;
	}

	public void setShape(PolygonPointsIterator shape) {
		this.shape = shape;
	}

	public void setPadding(Padding padding) {
		this.padding = padding;
	}

	public void setMeasurement(Measurement measurement) {
		this.measurement = measurement;
	}

	public Padding getPadding() {
		return padding;
	}

	public PolygonPointsIterator getShape() {
		return shape;
	}

	public Measurement getMeasurement() {
		return measurement;
	}

	public UIProperties copy() {
        try {
            return (UIProperties) BeanUtils.cloneBean(this);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected exception copying UIProperties", e);
        }
    }

	public void setBackgroundColor(UIPropertyProvider<ColorFactory.Color> backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public UIPropertyProvider<ColorFactory.Color> getBackgroundColor() {
		return backgroundColor;
	}

	public UIPropertyProvider<BorderFactory.Border> getBorder() {
		return border;
	}
}