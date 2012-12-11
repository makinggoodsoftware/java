package com.mgs.fantasi.ui.driver.swing;

import com.mgs.fantasi.measurements.Fraction;
import com.mgs.fantasi.measurements.Fractions;
import com.mgs.fantasi.measurements.Measurement;
import com.mgs.fantasi.measurements.Measurements;
import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.ui.driver.BaseUINativeElementCreatorStrategy;
import com.mgs.fantasi.ui.profile.BorderDefinition;
import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.ui.profile.UIStyle;
import com.mgs.fantasi.ui.wireframe.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Set;

public class SwingUINativeElementCreatorStrategy extends BaseUINativeElementCreatorStrategy<JPanel> {


	@Override
	protected JPanel newNonRectangularNativeElementSkeletonWithStyles(PolygonPointsIterator shape, Set<UIStyle> uiStyles) {
		return new JPanelWithDifferentShape (shape, uiStyles);
	}

	@Override
	protected void processSimpleStructure(JPanel nativeElement, Wireframe content, UIProfile uiProfile) {
		nativeElement.setLayout(new GridBagLayout());
		nativeElement.add(create(content, uiProfile), intoCoordinates(0, 0, Fractions.all(), Fractions.all()));
	}

	@Override
	protected JPanel decorateWithMargin(JPanel nativeElement, Margin margin) {
		JPanel marginContainer = new JPanel();
		marginContainer.setOpaque(false);
		marginContainer.setLayout(new GridBagLayout());
		int top = resolveMeasurement (margin.getTop());
		int right = resolveMeasurement (margin.getRight());
		int bottom = resolveMeasurement (margin.getBottom());
		int left = resolveMeasurement (margin.getLeft());
		marginContainer.setBorder(BorderFactory.createEmptyBorder(top, right, bottom, left));
		marginContainer.add(nativeElement, intoCoordinates(0,0, Fractions.all(), Fractions.all()));
		return marginContainer;
	}

	private int resolveMeasurement(Measurement measurement) {
		if (measurement instanceof Measurements.SimpleMeasurement){
			return ((Measurements.SimpleMeasurement) measurement).getIntValue();
		}
		return 0;
	}

	@Override
	protected void processLayerChilds(final JPanel parentNativeElement, Layers<Wireframe> childLayers, final UIProfile uiProfile) {
		parentNativeElement.setLayout(new OverlayLayout(parentNativeElement));
		childLayers.iterateInCrescendo(new LayerIterator<Wireframe>() {
			@Override
			public void on(int zIndex, Wireframe layer) {
				JPanel childLayerAsNativeElement = create(layer, uiProfile);
				parentNativeElement.add(childLayerAsNativeElement, zIndex);
			}
		});
	}

	@Override
	protected void processGridChilds(final JPanel parentNativeElement, Grid<Wireframe> childContent, final UIProfile uiProfile) {
		parentNativeElement.setLayout(new GridBagLayout());
		childContent.itereateCellsWith(new CellIterator<Wireframe>() {
			@Override
			public void on(int x, int y, CellContent<Wireframe> cell) {
				if (cell == null) {
					throw new RuntimeException
							("Error building the UI native element when inspecting the content of the original" +
									" wireframe. This should not happen ever! There must have been an error on the" +
									" render call previous to the transformation into a native UI element must be badly constructed");
				}
				Wireframe child = cell.getContent();
				JPanel childAsNativeComponent = create(child, uiProfile);
				parentNativeElement.add(childAsNativeComponent, intoCoordinates(x, y, cell.getWidthSizeRatio(), cell.getHeightSizeRatio()));
			}
		});

	}

	@Override
	protected JPanel newRectangularNativeElement() {
		JPanel jPanel = new JPanel();
		jPanel.setOpaque(false);
		return jPanel;
	}

	@Override
	public void applyStyle(UIStyle uiStyle, JPanel nativeElement) {
		BorderDefinition border = uiStyle.getBorder();
		if (border != null){
			Border nativeBorder = BorderFactory.createLineBorder(border.getColor(), border.getWidth());
			nativeElement.setBorder(nativeBorder);
		}
		Color backgroundColor = uiStyle.getBackgroundColor();
		if (backgroundColor!=null){
			nativeElement.setBackground(backgroundColor);
			nativeElement.setOpaque(true);
		}

	}

	private GridBagConstraints intoCoordinates(int x, int y, Fraction widthSizeRatio, Fraction heightSizeRatio) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = widthSizeRatio.toDouble();
		gbc.weighty = heightSizeRatio.toDouble();
		gbc.fill = GridBagConstraints.BOTH;
		return gbc;
	}
}
