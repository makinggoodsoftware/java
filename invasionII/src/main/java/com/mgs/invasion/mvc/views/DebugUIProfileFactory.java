package com.mgs.invasion.mvc.views;

import com.mgs.fantasi.profile.UIProfile;
import com.mgs.fantasi.profile.UIProfileFactory;
import com.mgs.fantasi.profile.UIStyle;
import com.mgs.fantasi.properties.polygon.HexagonShape;
import com.mgs.fantasi.properties.selectors.UISelectorFactory;
import com.mgs.fantasi.views.RectangleView;

import static com.mgs.fantasi.properties.BorderFactory.newBorder;
import static com.mgs.fantasi.properties.ColorFactory.newColorFromAwt;
import static java.awt.Color.*;

public class DebugUIProfileFactory implements UIProfileFactory {
	@Override
	public UIProfile getUIProfile() {
		UIProfile debugProfile = new UIProfile();

		debugProfile.addStyle(
				UISelectorFactory.forType(RectangleView.class),
				new UIStyle().
						withBorder(newBorder(newColorFromAwt(BLUE), 5)).
						withBackgroundColor(newColorFromAwt(GREEN))
		);
//
//		debugProfile.addStyle(
//				UISelectorFactory.forType(TwoLinesView.class),
//				new UIStyle().
//						withBorder(new BorderFactory(Color.ORANGE, 5)).
//						withBackgroundColor(Color.BLACK)
//		);
//
//		debugProfile.addStyle(
//				UISelectorFactory.forType(VerticalSlicesView.class),
//				new UIStyle().
//						withBorder(new BorderFactory(Color.CYAN, 5)).
//						withBackgroundColor(Color.MAGENTA)
//		);


		debugProfile.addStyle(
				UISelectorFactory.forShape(HexagonShape.class),
				new UIStyle().
						withBorder(newBorder(newColorFromAwt(YELLOW), 10)).
						withBackgroundColor(newColorFromAwt(RED))
		);

		return debugProfile;
	}
}