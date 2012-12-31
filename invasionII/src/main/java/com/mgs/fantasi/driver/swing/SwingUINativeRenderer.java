package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.UINativeRenderer;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.driver.swing.layoutConstruction.LayoutConstructionStrategy;
import com.mgs.fantasi.driver.swing.layoutConstruction.OnGoingLayoutBuildingStrategyFactory;
import com.mgs.fantasi.properties.BorderFactory;
import com.mgs.fantasi.properties.ColorFactory;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.properties.UIPropertyProvider;
import com.mgs.fantasi.properties.measurements.Fraction;
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
        JPanel uiNativeElement = jPanelCreationStrategyFactory.forUIProperties(uiPropertiesWitStyles).create(uiPropertiesWitStyles);
        LayoutConstructionStrategy<?> layoutConstructionStrategy = processStructure(view.buildContent());
        layoutConstructionStrategy.buildInto(uiNativeElement, this, uiProfile);
        return uiNativeElement;
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
