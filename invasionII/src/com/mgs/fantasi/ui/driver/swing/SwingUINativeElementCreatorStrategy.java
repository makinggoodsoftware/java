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
	protected JPanel newNonRectangularNativeElement(PolygonPointsIterator shape, Set<UIStyle> uiStyles) {
		return new JPanelWithDifferentShape (shape, uiStyles);
	}

	@Override
	protected JPanel newNativeRectangularElement() {
		return new JPanel();
	}


	@Override
	public void applyStyle(UIStyle uiStyle, JPanel nativeElement) {
		nativeElement.setBorder(createNativeBorder(uiStyle.getBorder()));
		nativeElement.setBackground(uiStyle.getBackgroundColor());

	}

	private Border createNativeBorder(BorderDefinition border) {
		return BorderFactory.createLineBorder(border.getColor(), border.getWidth());
	}

	@Override
	public void compose(JPanel parent, JPanel child, SizeStrategy sizeStrategy) {
		if (child==null) return;
		parent.setLayout(new BorderLayout());
		parent.add(child, BorderLayout.CENTER);
	}
}
