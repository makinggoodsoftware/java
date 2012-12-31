package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.properties.Padding;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.properties.measurements.Fractions;
import com.mgs.fantasi.properties.measurements.Measurement;
import com.mgs.fantasi.properties.measurements.Measurements;

import javax.swing.*;
import java.awt.*;

import static com.mgs.fantasi.driver.swing.SwingUINativeRenderer.coordinates;

public class DecoratedJPanelWithPadding implements JPanelCreationStrategy {
    private final JPanelCreationStrategy decoratedPanel;

    public DecoratedJPanelWithPadding(JPanelCreationStrategy decoratedPanel) {
        this.decoratedPanel = decoratedPanel;
    }

    @Override
    public JPanel create(UIProperties uiProperties) {
        JPanel jPanel = decoratedPanel.create(uiProperties);
        JPanel outmostPointer = jPanel;
        Padding padding = uiProperties.getPadding();
        if (! padding.isEmpty()){
            outmostPointer = decorateWithPadding(jPanel, padding);
        }
        return outmostPointer;
    }

    protected final JPanel decorateWithPadding(JPanel nativeElement, Padding padding){
        JPanel paddingContainer = new JPanel();
        paddingContainer.setOpaque(false);
        paddingContainer.setLayout(new GridBagLayout());
        int top = resolveMeasurement (padding.getTop());
        int right = resolveMeasurement (padding.getRight());
        int bottom = resolveMeasurement (padding.getBottom());
        int left = resolveMeasurement (padding.getLeft());
        paddingContainer.setBorder(javax.swing.BorderFactory.createEmptyBorder(top, right, bottom, left));
        paddingContainer.add(nativeElement, coordinates(0, 0, Fractions.all(), Fractions.all()));
        return paddingContainer;
    }

    private int resolveMeasurement(Measurement measurement) {
        if (measurement instanceof Measurements.SimpleMeasurement){
            return ((Measurements.SimpleMeasurement) measurement).getIntValue();
        }
        return 0;
    }

    public JPanelCreationStrategy getDecoratedPanel() {
        return decoratedPanel;
    }
}
