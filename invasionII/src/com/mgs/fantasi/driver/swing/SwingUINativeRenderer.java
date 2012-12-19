package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.UINativeRenderer;
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
import com.mgs.fantasi.rendering.structure.grid.CellContent;
import com.mgs.fantasi.rendering.structure.grid.CellIterator;
import com.mgs.fantasi.rendering.structure.grid.GridStructure;
import com.mgs.fantasi.rendering.structure.layer.LayerIterator;
import com.mgs.fantasi.rendering.structure.layer.LayeredStructure;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class SwingUINativeRenderer implements UINativeRenderer<JPanel> {
	@Override
	public JPanel render(Renderable renderable) {
		JPanel uiNativeElement = createUINativeElementSkeleton(renderable.getUIProperties());
		processStructure(renderable.getContent(), uiNativeElement);

		JPanel outmostPointer = uiNativeElement;
		Margin margin = renderable.getMargin();
		if (! margin.isEmpty()){
			outmostPointer = decorateWithMargin (uiNativeElement, margin);
		}
		return outmostPointer;
	}

	private JPanel createUINativeElementSkeleton(UIProperties uiProperties) {
		PolygonPointsIterator shape = uiProperties.getShape();
		return shape.isRectangular() ?
			newRectangularNativeElementSkeletonWithStyles(uiProperties):
			newNonRectangularNativeElementSkeletonWithStyles(uiProperties);
	}

	private void processStructure(Structure<Renderable> content, final JPanel nativeContainer) {
		switch (content.getType()){
			case GRID:
				final OnGoingLayoutConstruction<GridBagConstraints> onGoingLayoutConstruction = new OnGoingLayoutBuildingStrategyFactory().grid();
				((GridStructure<Renderable>) content).itereateCellsWith(new CellIterator<Renderable>() {
					@Override
					public void on(int x, int y, CellContent<Renderable> cell) {
						Renderable child = cell.getContent();
						JPanel childAsNativeComponent = render(child);
						onGoingLayoutConstruction.add(childAsNativeComponent).into(coordinates(x, y, cell.getWidthSizeRatio(), cell.getHeightSizeRatio()));
					}
				});
				onGoingLayoutConstruction.buildInto(nativeContainer);
				break;
			case LAYERS:
				nativeContainer.setLayout(new OverlayLayout(nativeContainer));
				((LayeredStructure<Renderable>) content).iterateInCrescendo(new LayerIterator<Renderable>() {
					@Override
					public void on(int zIndex, Renderable layer) {
						JPanel childLayerAsNativeElement = render(layer);
						nativeContainer.add(childLayerAsNativeElement, zIndex);
					}
				});
				break;
			case SIMPLE:
				Renderable renderable = ((SimpleStructure<Renderable>) content).getContent();
				if (renderable!=null) processSimpleStructure(nativeContainer, renderable);
				break;
			case EMPTY:
				processEmptyStructure(nativeContainer);
				break;
			case DELEGATE:
				Structure<Renderable> delegate = ((DelegateStructure<Renderable>) content).getContent();
				processStructure(delegate, nativeContainer);
				break;
			default:
				throw new RuntimeException("Can't process the structure: " + content);
		}
	}

	protected final void processEmptyStructure(JPanel nativeContainer){

	}

	protected final void processSimpleStructure(JPanel nativeElement, Renderable content){
		nativeElement.setLayout(new GridBagLayout());
		nativeElement.add(render(content), coordinates(0, 0, Fractions.all(), Fractions.all()));
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
		applyUIProperties (jPanel, uiProperties);
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

	private GridBagConstraints coordinates(int x, int y, Fraction widthSizeRatio, Fraction heightSizeRatio) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = widthSizeRatio.toDouble();
		gbc.weighty = heightSizeRatio.toDouble();
		gbc.fill = GridBagConstraints.BOTH;
		return gbc;
	}

	private static class OnGoingLayoutBuildingStrategyFactory {
		public OnGoingLayoutConstruction grid(){
			return new OnGoingLayoutConstruction<GridBagConstraints>(new GridBagLayout());
		}
	}

	private static class OnGoingLayoutConstruction<T>{
		private final LayoutManager layout;
		private List<OnGoingChildContentConstruction<T>> toBeAdded = new ArrayList<OnGoingChildContentConstruction<T>>();

		private OnGoingLayoutConstruction(LayoutManager layout) {
			this.layout = layout;
		}

		public OnGoingChildContentConstruction<T> add(JPanel childAsNativeComponent) {
			return new OnGoingChildContentConstruction(this, childAsNativeComponent);
		}

		public void buildInto(JPanel container){
			container.setLayout(getSwingLayout());
			for (OnGoingChildContentConstruction onGoingCellLayoutConstruction : toBeAdded) {
				container.add(onGoingCellLayoutConstruction.getCellContent(), onGoingCellLayoutConstruction.getSpecifics());
			}
		}

		private LayoutManager getSwingLayout() {
			return layout;
		}

		public void doAdd(OnGoingChildContentConstruction<T> onGoingCellLayoutConstruction) {
			toBeAdded.add(onGoingCellLayoutConstruction);
		}
	}

	private static class OnGoingChildContentConstruction <T>{
		private final OnGoingLayoutConstruction onGoingLayoutConstruction;
		private final JPanel cellContent;
		private T gridBagConstraints;

		public OnGoingChildContentConstruction(OnGoingLayoutConstruction onGoingLayoutConstruction, JPanel cellContent) {
			this.onGoingLayoutConstruction = onGoingLayoutConstruction;
			this.cellContent = cellContent;
		}

		public void into(T gridBagConstraints) {
			this.gridBagConstraints = gridBagConstraints;
			onGoingLayoutConstruction.doAdd(this);
		}

		public JPanel getCellContent() {
			return cellContent;
		}

		public T getSpecifics() {
			return gridBagConstraints;
		}
	}
}
