package com.mgs.invasion.mvc.view;

import com.mgs.fantasi.polygon.HexagonShape;
import com.mgs.fantasi.ui.profile.BorderDefinition;
import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.ui.profile.UIProfileFactory;
import com.mgs.fantasi.ui.profile.UIStyle;
import com.mgs.fantasi.ui.selectors.UISelectorFactory;

import java.awt.*;

public class ProductionUiProfileFactory implements UIProfileFactory {

	@Override
	public UIProfile getUIProfile() {
		UIProfile debugProfile = new UIProfile();

		debugProfile.addStyle(
				UISelectorFactory.forShape(HexagonShape.class),
				new UIStyle().withBorder(new BorderDefinition(Color.BLACK, 1))
		);

		return debugProfile;
	}
}
