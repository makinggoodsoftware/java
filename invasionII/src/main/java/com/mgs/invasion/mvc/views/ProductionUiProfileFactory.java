package com.mgs.invasion.mvc.views;

import com.mgs.fantasi.properties.polygon.HexagonShape;
import com.mgs.fantasi.selectors.UISelectorFactory;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.styles.UIProfileFactory;
import com.mgs.fantasi.styles.UIStyle;

import java.awt.*;

import static com.mgs.fantasi.properties.BorderFactory.newBorder;
import static com.mgs.fantasi.properties.ColorFactory.newColorFromAwt;

@SuppressWarnings("unused")
public class ProductionUiProfileFactory implements UIProfileFactory {

	@Override
	public UIProfile getUIProfile() {
		UIProfile profile = new UIProfile();

		profile.addStyle(
				UISelectorFactory.forShape(HexagonShape.class),
				new UIStyle().withBorder(newBorder(newColorFromAwt(Color.BLACK), 1))
		);

		profile.addStyle(
				UISelectorFactory.forName("2ndLayerOfHexagons"),
				new UIStyle().withBorder(newBorder(newColorFromAwt(Color.BLACK), 1))
		);

		return profile;
	}
}
