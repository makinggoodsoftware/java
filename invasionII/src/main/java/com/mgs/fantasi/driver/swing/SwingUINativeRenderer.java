package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.UINativeRenderer;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategy;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.driver.swing.layoutConstruction.LayoutConstructionManager;
import com.mgs.fantasi.driver.swing.layoutConstruction.LayoutConstructionStrategy;
import com.mgs.fantasi.driver.swing.layoutConstruction.OnGoingChildAddition;
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
	private final RenderingProcessFactory renderingProcessFactory;

	public SwingUINativeRenderer(RenderingProcessFactory renderingProcessFactory) {
		this.renderingProcessFactory = renderingProcessFactory;
	}

	@Override
	public JPanel render(View view, UIProfile uiProfile) {
		RenderingProcess renderingProcess = renderingProcessFactory.newRenderingProcess(uiProfile);
		JPanelCreationInstructions jPanelConstructionInstructions = renderingProcess.createJPanelConstructionInstructions(view);
		return renderingProcess.createJPanel(jPanelConstructionInstructions);
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

    public static class JPanelCreationInstructions {
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

	public static class RenderingProcess {
		private final UIProfile uiProfile;
		private final LayoutConstructionManager layoutConstructionManager;
		private final StyleManager styleManager;
		private final JPanelCreationStrategyFactory jPanelCreationStrategyFactory;

		public RenderingProcess(UIProfile uiProfile, LayoutConstructionManager layoutConstructionManager, StyleManager styleManager, JPanelCreationStrategyFactory jPanelCreationStrategyFactory) {
			this.uiProfile = uiProfile;
			this.layoutConstructionManager = layoutConstructionManager;
			this.styleManager = styleManager;
			this.jPanelCreationStrategyFactory = jPanelCreationStrategyFactory;
		}

		public JPanelCreationInstructions createJPanelConstructionInstructions(View view) {
			UIProperties uiPropertiesWithStylesApplied = styleManager.applyStyles(view.getUiProperties(), uiProfile.findStylesFor(view));
			JPanelCreationStrategy outsideCreationStrategy = jPanelCreationStrategyFactory.forUIProperties(uiPropertiesWithStylesApplied);
			LayoutConstructionStrategy<?, ? extends Wireframe> insideConstructionStrategy = layoutConstructionManager.createAndFillLayout(view.buildContent());
			return new JPanelCreationInstructions(outsideCreationStrategy, insideConstructionStrategy);
		}

		public JPanel createJPanel(JPanelCreationInstructions jPanelCreationInstructions) {
			JPanel container = jPanelCreationInstructions.getOutsideCreationStrategy().create();
			LayoutConstructionStrategy<?, ? extends Wireframe> insideConstructionStrategy = jPanelCreationInstructions.getInsideConstructionStrategy();
			if (insideConstructionStrategy.isEmpty()) return container;

			container.setLayout(insideConstructionStrategy.getLayoutManager(container));
			for (OnGoingChildAddition onGoingChildAddition : insideConstructionStrategy.getToBeAdded()) {
				View content = onGoingChildAddition.getContent();
				JPanel childUiNativeElement = createJPanel(createJPanelConstructionInstructions(content));
				container.add(childUiNativeElement, onGoingChildAddition.getSpecifics());
			}
			return container;
		}
	}

	public static class RenderingProcessFactory {
		private final LayoutConstructionManager layoutConstructionManager;
		private final StyleManager styleManager;
		private final JPanelCreationStrategyFactory jPanelCreationStrategyFactory;

		public RenderingProcessFactory(LayoutConstructionManager layoutConstructionManager, StyleManager styleManager, JPanelCreationStrategyFactory jPanelCreationStrategyFactory) {
			this.layoutConstructionManager = layoutConstructionManager;
			this.styleManager = styleManager;
			this.jPanelCreationStrategyFactory = jPanelCreationStrategyFactory;
		}

		public RenderingProcess newRenderingProcess(UIProfile uiProfile) {
			return new RenderingProcess(uiProfile, layoutConstructionManager, styleManager, jPanelCreationStrategyFactory);
		}
	}
}
