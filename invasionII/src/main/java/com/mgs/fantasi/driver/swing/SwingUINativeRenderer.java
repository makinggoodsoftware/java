package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.UINativeRenderer;
import com.mgs.fantasi.driver.swing.layoutConstruction.LayoutConstructionStrategy;
import com.mgs.fantasi.driver.swing.layoutConstruction.OnGoingLayoutBuildingStrategyFactory;
import com.mgs.fantasi.profile.PropertyDefinition;
import com.mgs.fantasi.properties.BorderDefinition;
import com.mgs.fantasi.properties.ColorFactory;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.properties.measurements.Fraction;
import com.mgs.fantasi.properties.measurements.Fractions;
import com.mgs.fantasi.properties.measurements.Measurement;
import com.mgs.fantasi.properties.measurements.Measurements;
import com.mgs.fantasi.rendering.Padding;
import com.mgs.fantasi.rendering.Renderable;
import com.mgs.fantasi.rendering.structure.DelegateStructure;
import com.mgs.fantasi.rendering.structure.SimpleStructure;
import com.mgs.fantasi.rendering.structure.Structure;
import com.mgs.fantasi.rendering.structure.grid.GridStructure;
import com.mgs.fantasi.rendering.structure.layer.LayeredStructure;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public final class SwingUINativeRenderer implements UINativeRenderer<JPanel> {

	private final OnGoingLayoutBuildingStrategyFactory layoutStrategyFactory = new OnGoingLayoutBuildingStrategyFactory();

	@Override
	public JPanel render(Renderable renderable) {
		JPanel uiNativeElement = createUINativeElementSkeleton(renderable.getUIProperties());
		LayoutConstructionStrategy<?> layoutConstructionStrategy = processStructure(renderable.getContent());
		layoutConstructionStrategy.buildInto(uiNativeElement, this);
		return uiNativeElement;
	}

	public JPanel createUINativeElementSkeleton(UIProperties uiProperties) {
		JPanel content = uiProperties.getShape().isRectangular() ?
				newRectangularNativeElementSkeletonWithStyles(uiProperties) :
				newNonRectangularNativeElementSkeletonWithStyles(uiProperties);

		JPanel outmostPointer = content;
		Padding padding = uiProperties.getPadding();
		if (! padding.isEmpty()){
			outmostPointer = decorateWithMargin (content, padding);
		}
		return outmostPointer;
	}

	public LayoutConstructionStrategy<?> processStructure(Structure<Renderable> content) {
		switch (content.getType()){
			case GRID:
				return layoutStrategyFactory.grid().from((GridStructure<Renderable>) content);
			case LAYERS:
				return layoutStrategyFactory.layers().from((LayeredStructure<Renderable>) content);
			case SIMPLE:
				return layoutStrategyFactory.simple().from((SimpleStructure<Renderable>) content);
			case DELEGATE:
				Structure<Renderable> delegate = ((DelegateStructure<Renderable>) content).getContent();
				return processStructure(delegate);
			case EMPTY:
				return new OnGoingLayoutBuildingStrategyFactory().empty();
			default:
				throw new RuntimeException("Can't process the structure: " + content);
		}
	}

	protected final JPanel decorateWithMargin(JPanel nativeElement, Padding padding){
		JPanel marginContainer = new JPanel();
		marginContainer.setOpaque(false);
		marginContainer.setLayout(new GridBagLayout());
		int top = resolveMeasurement (padding.getTop());
		int right = resolveMeasurement (padding.getRight());
		int bottom = resolveMeasurement (padding.getBottom());
		int left = resolveMeasurement (padding.getLeft());
		marginContainer.setBorder(BorderFactory.createEmptyBorder(top, right, bottom, left));
		marginContainer.add(nativeElement, coordinates(0, 0, Fractions.all(), Fractions.all()));
		return marginContainer;
	}

	private int resolveMeasurement(Measurement measurement) {
		if (measurement instanceof Measurements.SimpleMeasurement){
			return ((Measurements.SimpleMeasurement) measurement).getIntValue();
		}
		return 0;
	}


	private JPanel newRectangularNativeElementSkeletonWithStyles(UIProperties uiProperties) {
		JPanel jPanel = newRectangularNativeElement();
		applyUIProperties(jPanel, uiProperties);
		return jPanel;
	}

	private void applyUIProperties(JPanel jPanel, UIProperties uiProperties) {
        PropertyDefinition<ColorFactory.Color> backgroundColor = uiProperties.getBackgroundColor();
        if (backgroundColor.isDefined()){
            jPanel.setBackground(backgroundColor.getData().getColorAsAwt());
        }
		PropertyDefinition<BorderDefinition.BorderUI> border = uiProperties.getBorder();
		if (border.isDefined()){
            PropertyDefinition<ColorFactory.Color> colorDefinition = border.getData().getColor();
            if (colorDefinition.isDefined()){
                Border lineBorder = BorderFactory.createLineBorder(colorDefinition.getData().getColorAsAwt(), border.getData().getWidth());
			    jPanel.setBorder(lineBorder);
            }else{
                throw new RuntimeException("Can't paint the border without a color!!!");
            }

		}else{
			jPanel.setBorder(null);
		}

	}

	protected final JPanel newRectangularNativeElement(){
		JPanel jPanel = new JPanel();
		jPanel.setOpaque(false);
		return jPanel;
	}

	protected final JPanel newNonRectangularNativeElementSkeletonWithStyles(UIProperties uiProperties){
		return new JPanelWithDifferentShape(uiProperties.getShape(), uiProperties);
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
