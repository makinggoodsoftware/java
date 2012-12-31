package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.UINativeRenderer;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategy;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.driver.swing.layoutConstruction.LayoutConstructionManager;
import com.mgs.fantasi.driver.swing.layoutConstruction.LayoutConstructionStrategy;
import com.mgs.fantasi.properties.BorderFactory;
import com.mgs.fantasi.properties.ColorFactory;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.properties.UIPropertyProvider;
import com.mgs.fantasi.properties.measurements.Fraction;
import com.mgs.fantasi.rendering.wireframe.Wireframe;
import com.mgs.fantasi.styles.StyleManager;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.views.View;

import javax.swing.*;
import java.awt.*;

public final class SwingUINativeRenderer implements UINativeRenderer<JPanel> {
    private final StyleManager styleManager;
    private final JPanelCreationStrategyFactory jPanelCreationStrategyFactory;
    private final LayoutConstructionManager layoutConstructionManager;

    public SwingUINativeRenderer(StyleManager styleManager, JPanelCreationStrategyFactory jPanelCreationStrategyFactory, LayoutConstructionManager layoutConstructionManager) {
		this.styleManager = styleManager;
        this.jPanelCreationStrategyFactory = jPanelCreationStrategyFactory;
        this.layoutConstructionManager = layoutConstructionManager;
    }

	@Override
	public JPanel render(View view, UIProfile uiProfile) {
        return createJPanel(uiProfile, createJPanelConstructionInstructions(view, uiProfile));
	}

    public JPanelCreationInstructions createJPanelConstructionInstructions(View view, UIProfile uiProfile) {
        UIProperties uiPropertiesWithStylesApplied = styleManager.applyStyles(view.getUiProperties(), uiProfile.findStylesFor(view));
        JPanelCreationStrategy outsideCreationStrategy = jPanelCreationStrategyFactory.forUIProperties(uiPropertiesWithStylesApplied);
        LayoutConstructionStrategy<?, ? extends Wireframe> insideConstructionStrategy = layoutConstructionManager.createAndFillLayout(view.buildContent());
        return new JPanelCreationInstructions(outsideCreationStrategy, insideConstructionStrategy);
    }

    private JPanel createJPanel(UIProfile uiProfile, JPanelCreationInstructions jPanelCreationInstructions) {
        JPanel uiNativeElement = jPanelCreationInstructions.getOutsideCreationStrategy().create();
        jPanelCreationInstructions.getInsideConstructionStrategy().buildInto(uiNativeElement, this, uiProfile);
        return uiNativeElement;
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

    private static class JPanelCreationInstructions {
        private final JPanelCreationStrategy outsideCreationStrategy;
        private final LayoutConstructionStrategy<?, ? extends Wireframe> insideConstructionStrategy;

        public JPanelCreationInstructions(JPanelCreationStrategy outsideCreationStrategy, LayoutConstructionStrategy<?, ? extends Wireframe> insideConstructionStrategy) {
            this.outsideCreationStrategy = outsideCreationStrategy;
            this.insideConstructionStrategy = insideConstructionStrategy;
        }

        public JPanelCreationStrategy getOutsideCreationStrategy() {
            return outsideCreationStrategy;
        }

        public LayoutConstructionStrategy<?, ? extends Wireframe> getInsideConstructionStrategy() {
            return insideConstructionStrategy;
        }
    }
}
