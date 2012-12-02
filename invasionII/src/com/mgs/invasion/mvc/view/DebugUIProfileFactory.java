package com.mgs.invasion.mvc.view;

import com.mgs.fantasi.polygon.HexagonShape;
import com.mgs.fantasi.ui.profile.BorderDefinition;
import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.ui.profile.UIProfileFactory;
import com.mgs.fantasi.ui.profile.UIStyle;
import com.mgs.fantasi.ui.selectors.UISelectorFactory;

import java.awt.*;

public class DebugUIProfileFactory implements UIProfileFactory {
	@Override
	public UIProfile getUIProfile() {
		UIProfile debugProfile = new UIProfile();

//		debugProfile.addStyle(
//				UISelectorFactory.forType(RectangleStructureBuilder.class),
//				new UIStyle().
//						withBorder(new BorderDefinition(Color.BLUE, 5)).
//						withBackground(Color.GREEN)
//		);
//
//		debugProfile.addStyle(
//				UISelectorFactory.forType(TwoLinesStructureBuilder.class),
//				new UIStyle().
//						withBorder(new BorderDefinition(Color.ORANGE, 5)).
//						withBackground(Color.BLACK)
//		);
//
//		debugProfile.addStyle(
//				UISelectorFactory.forType(VerticalSlicesStructureBuilder.class),
//				new UIStyle().
//						withBorder(new BorderDefinition(Color.CYAN, 5)).
//						withBackground(Color.MAGENTA)
//		);


		debugProfile.addStyle(
				UISelectorFactory.forShape(HexagonShape.class),
				new UIStyle().
						withBorder(new BorderDefinition(Color.YELLOW, 10)).
						withBackground(Color.RED)
		);

		return debugProfile;
	}
}
