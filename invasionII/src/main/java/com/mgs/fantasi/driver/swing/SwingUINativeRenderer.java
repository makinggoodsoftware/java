package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.UINativeRenderer;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategy;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.driver.swing.layoutConstruction.LayoutConstructionStrategy;
import com.mgs.fantasi.driver.swing.layoutConstruction.OnGoingLayoutBuildingStrategyFactory;
import com.mgs.fantasi.properties.BorderFactory;
import com.mgs.fantasi.properties.*;
import com.mgs.fantasi.properties.measurements.Fraction;
import com.mgs.fantasi.properties.measurements.Fractions;
import com.mgs.fantasi.properties.measurements.Measurement;
import com.mgs.fantasi.properties.measurements.Measurements;
import com.mgs.fantasi.rendering.wireframe.*;
import com.mgs.fantasi.styles.StyleManager;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.views.View;

import javax.swing.*;
import java.awt.*;

public final class SwingUINativeRenderer implements UINativeRenderer<JPanel> {
	private final OnGoingLayoutBuildingStrategyFactory layoutStrategyFactory = new OnGoingLayoutBuildingStrategyFactory();
	private final StyleManager styleManager;
    private final JPanelCreationStrategyFactory jPanelCreationStrategyFactory;

    public SwingUINativeRenderer(StyleManager styleManager, JPanelCreationStrategyFactory jPanelCreationStrategyFactory) {
		this.styleManager = styleManager;
        this.jPanelCreationStrategyFactory = jPanelCreationStrategyFactory;
    }

	@Override
	public JPanel render(View view, UIProfile uiProfile) {
		UIProperties uiPropertiesWitStyles = styleManager.applyStyles(view.getUiProperties(), uiProfile.findStylesFor(view));
		JPanel uiNativeElement = createUINativeElementSkeleton(uiPropertiesWitStyles);
        LayoutConstructionStrategy<?> layoutConstructionStrategy = processStructure(view.buildContent());
        layoutConstructionStrategy.buildInto(uiNativeElement, this, uiProfile);
        return uiNativeElement;
	}

    public JPanel createUINativeElementSkeleton(UIProperties uiProperties) {
        JPanelCreationStrategy jPanelCreationStrategy = jPanelCreationStrategyFactory.getJPanelCreationStrategy(uiProperties);
        JPanel jPanel = jPanelCreationStrategy.create(uiProperties);

        JPanel outmostPointer = jPanel;
		Padding padding = uiProperties.getPadding();
		if (! padding.isEmpty()){
			outmostPointer = decorateWithPadding(jPanel, padding);
		}
		return outmostPointer;
	}

    public LayoutConstructionStrategy<?> processStructure(Wireframe content) {
		switch (content.getType()){
			case GRID:
				return layoutStrategyFactory.grid().from((GridWireframe) content);
			case LAYERS:
				return layoutStrategyFactory.layers().from((LayeredWireframe) content);
			case SIMPLE:
				return layoutStrategyFactory.simple().from((RectangleWireframe) content);
			case DELEGATE:
				Wireframe delegate = ((DelegateWireframe) content).getContent();
				return processStructure(delegate);
			case EMPTY:
				return new OnGoingLayoutBuildingStrategyFactory().empty();
			default:
				throw new RuntimeException("Can't process the structure: " + content);
		}
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


    public static void applyUIProperties(JPanel jPanel, UIProperties uiProperties) {
        UIPropertyProvider<ColorFactory.Color> backgroundColor = uiProperties.getBackgroundColor();
        if (backgroundColor.isDefined() &&  ! backgroundColor.getData().isTransparent()){
            jPanel.setBackground(backgroundColor.getData().getColorAsAwt());
        }
		UIPropertyProvider<BorderFactory.Border> border = uiProperties.getBorder();
		if (border.isDefined() && border.getData().getWidth() > 0){
            UIPropertyProvider<ColorFactory.Color> colorProviderUI = border.getData().getColor();
            if (colorProviderUI.isDefined()){
                ColorFactory.Color colorData = colorProviderUI.getData();
                Color lineColor = colorData.isTransparent() ? Color.ORANGE : colorData.getColorAsAwt();
                javax.swing.border.Border lineBorder = javax.swing.BorderFactory.createLineBorder(lineColor, border.getData().getWidth());
			    jPanel.setBorder(lineBorder);
            }else{
                throw new RuntimeException("Can't paint the border without a color!!!");
            }

		}else{
			jPanel.setBorder(null);
		}
	}

    public static GridBagConstraints coordinates(int x, int y, Fraction widthSizeRatio, Fraction heightSizeRatio) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = widthSizeRatio.toDouble();
		gbc.weighty = heightSizeRatio.toDouble();
		gbc.fill = GridBagConstraints.BOTH;
		return gbc;
	}

}
