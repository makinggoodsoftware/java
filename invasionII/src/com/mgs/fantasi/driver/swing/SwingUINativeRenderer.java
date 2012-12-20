package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.UINativeRenderer;
import com.mgs.fantasi.driver.swing.layoutConstruction.OnGoingLayoutBuildingStrategyFactory;
import com.mgs.fantasi.driver.swing.layoutConstruction.OnGoingLayoutConstruction;
import com.mgs.fantasi.properties.BorderDefinition;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.properties.measurements.Fraction;
import com.mgs.fantasi.properties.measurements.Fractions;
import com.mgs.fantasi.properties.measurements.Measurement;
import com.mgs.fantasi.properties.measurements.Measurements;
import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;
import com.mgs.fantasi.rendering.Margin;
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
		OnGoingLayoutConstruction<?> onGoingLayoutConstruction = processStructure(renderable.getContent());
		onGoingLayoutConstruction.buildInto(uiNativeElement, this);

		JPanel outmostPointer = uiNativeElement;
		Margin margin = renderable.getMargin();
		if (! margin.isEmpty()){
			outmostPointer = decorateWithMargin (uiNativeElement, margin);
		}
		return outmostPointer;
	}

	public JPanel createUINativeElementSkeleton(UIProperties uiProperties) {
		PolygonPointsIterator shape = uiProperties.getShape();
		return shape.isRectangular() ?
			newRectangularNativeElementSkeletonWithStyles(uiProperties):
			newNonRectangularNativeElementSkeletonWithStyles(uiProperties);
	}

	public OnGoingLayoutConstruction<?> processStructure(Structure<Renderable> content) {
		switch (content.getType()){
			case GRID:
				return layoutStrategyFactory.grid().from((GridStructure<Renderable>) content);
			case LAYERS:
				return layoutStrategyFactory.layers().from((LayeredStructure<Renderable>) content);
			case SIMPLE:
				final OnGoingLayoutConstruction<GridBagConstraints> onGoingLayoutConstruction3= new OnGoingLayoutBuildingStrategyFactory().grid();
				Renderable renderable = ((SimpleStructure<Renderable>) content).getContent();
				if (renderable!=null) {
					onGoingLayoutConstruction3.add(renderable).into(coordinates(0, 0, Fractions.all(), Fractions.all()));
				}
				return onGoingLayoutConstruction3;
			case DELEGATE:
				Structure<Renderable> delegate = ((DelegateStructure<Renderable>) content).getContent();
				return processStructure(delegate);
			case EMPTY:
				return new OnGoingLayoutBuildingStrategyFactory().empty();
			default:
				throw new RuntimeException("Can't process the structure: " + content);
		}
	}

	protected final JPanel decorateWithMargin(JPanel nativeElement, Margin margin){
		JPanel marginContainer = new JPanel();
		marginContainer.setOpaque(false);
		marginContainer.setLayout(new GridBagLayout());
		int top = resolveMeasurement (margin.getTop());
		int right = resolveMeasurement (margin.getRight());
		int bottom = resolveMeasurement (margin.getBottom());
		int left = resolveMeasurement (margin.getLeft());
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
		jPanel.setBackground(uiProperties.getBackgroundColor());
		BorderDefinition border = uiProperties.getBorder();
		if (border!=null){
			Border lineBorder = BorderFactory.createLineBorder(border.getColor(), border.getWidth());
			jPanel.setBorder(lineBorder);
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
