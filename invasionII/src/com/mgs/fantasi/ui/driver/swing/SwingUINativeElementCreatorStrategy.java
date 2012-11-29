package com.mgs.fantasi.ui.driver.swing;

import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.ui.driver.BaseUINativeElementCreatorStrategy;
import com.mgs.fantasi.ui.profile.BorderDefinition;
import com.mgs.fantasi.ui.profile.SizeStrategy;
import com.mgs.fantasi.ui.profile.UIStyle;

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
	protected void createLayoutSkeleton(JPanel nativeElement) {
		nativeElement.setLayout(new GridBagLayout());
	}

	@Override
	protected JPanel newRectangularNativeElement() {
		return new JPanel();
	}

	@Override
	public void applyStyle(UIStyle uiStyle, JPanel nativeElement) {
		BorderDefinition border = uiStyle.getBorder();
		Border nativeBorder = BorderFactory.createLineBorder(border.getColor(), border.getWidth());
		nativeElement.setBorder(nativeBorder);
		nativeElement.setBackground(uiStyle.getBackgroundColor());

	}

	@Override
	public void compose(JPanel parent, JPanel child, SizeStrategy sizeStrategy, int x, int y) {
		parent.add(child, intoCoordinates(x, y));
	}

	private GridBagConstraints intoCoordinates(int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = x;
		return gbc;
	}
}
