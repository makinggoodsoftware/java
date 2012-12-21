package com.mgs.invasion.mvc.views;

import com.mgs.fantasi.profile.UIProfile;
import com.mgs.fantasi.profile.UIProfileFactory;
import com.mgs.fantasi.profile.UIStyle;
import com.mgs.fantasi.properties.BorderDefinition;
import com.mgs.fantasi.properties.polygon.HexagonShape;
import com.mgs.fantasi.properties.selectors.UISelectorFactory;
import com.mgs.fantasi.views.RectangleView;

import static com.mgs.fantasi.properties.ColorDefinition.colorFromAwtColor;
import static java.awt.Color.*;

public class DebugUIProfileFactory implements UIProfileFactory {
	@Override
	public UIProfile getUIProfile() {
		UIProfile debugProfile = new UIProfile();

		debugProfile.addStyle(
				UISelectorFactory.forType(RectangleView.class),
				new UIStyle().
						withBorder(new BorderDefinition(colorFromAwtColor(BLUE), 5)).
						withBackground(colorFromAwtColor(GREEN))
		);
//
//		debugProfile.addStyle(
//				UISelectorFactory.forType(TwoLinesView.class),
//				new UIStyle().
//						withBorder(new BorderDefinition(Color.ORANGE, 5)).
//						withBackground(Color.BLACK)
//		);
//
//		debugProfile.addStyle(
//				UISelectorFactory.forType(VerticalSlicesView.class),
//				new UIStyle().
//						withBorder(new BorderDefinition(Color.CYAN, 5)).
//						withBackground(Color.MAGENTA)
//		);


		debugProfile.addStyle(
				UISelectorFactory.forShape(HexagonShape.class),
				new UIStyle().
						withBorder(new BorderDefinition(colorFromAwtColor(YELLOW), 10)).
						withBackground(colorFromAwtColor(RED))
		);

		return debugProfile;
	}
}
