package com.mgs.invasion.mvc.views;

import com.mgs.fantasi.properties.polygon.HexagonShape;
import com.mgs.fantasi.selectors.UISelectorFactory;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.styles.UIProfileFactory;
import com.mgs.fantasi.styles.UIStyle;
import com.mgs.fantasi.wireframeTreeBuilders.RectangleWireframeTreeBuilder;

import static com.mgs.fantasi.properties.BorderFactory.newBorder;
import static com.mgs.fantasi.properties.ColorFactory.newColorFromAwt;
import static java.awt.Color.*;

public class DebugUIProfileFactory implements UIProfileFactory {
	@Override
	public UIProfile getUIProfile() {
		UIProfile debugProfile = new UIProfile();

		debugProfile.addStyle(
				UISelectorFactory.forType(RectangleWireframeTreeBuilder.class),
				new UIStyle().
						withBorder(newBorder(newColorFromAwt(BLUE), 5)).
						withBackgroundColor(newColorFromAwt(GREEN))
		);
		debugProfile.addStyle(
				UISelectorFactory.forShape(HexagonShape.class),
				new UIStyle().
						withBorder(newBorder(newColorFromAwt(YELLOW), 10)).
						withBackgroundColor(newColorFromAwt(RED))
		);

		return debugProfile;
	}
}
