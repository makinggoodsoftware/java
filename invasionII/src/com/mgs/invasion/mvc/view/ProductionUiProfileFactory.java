package com.mgs.invasion.mvc.view;

import com.mgs.fantasi.profile.UIProfile;
import com.mgs.fantasi.profile.UIProfileFactory;
import com.mgs.fantasi.profile.UIStyle;
import com.mgs.fantasi.properties.BorderDefinition;
import com.mgs.fantasi.properties.polygon.HexagonShape;
import com.mgs.fantasi.properties.selectors.UISelectorFactory;

import java.awt.*;

public class ProductionUiProfileFactory implements UIProfileFactory {

	@Override
	public UIProfile getUIProfile() {
		UIProfile profile = new UIProfile();

		profile.addStyle(
				UISelectorFactory.forShape(HexagonShape.class),
				new UIStyle().withBorder(new BorderDefinition(Color.BLACK, 1))
		);

		profile.addStyle(
				UISelectorFactory.forName("2ndLayerOfHexagons"),
				new UIStyle().withBorder(new BorderDefinition(Color.BLACK, 1))
		);

		return profile;
	}
}
