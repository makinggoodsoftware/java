package com.mgs.invasion.mvc.views;

import com.mgs.fantasi.properties.data.polygon.HexagonShape;
import com.mgs.fantasi.selectors.UISelectorFactory;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.styles.UIProfileFactory;

import java.awt.*;

import static com.mgs.fantasi.properties.UIPropertiesBuilderFactory.allUndefined;
import static com.mgs.fantasi.properties.UIPropertyFactory.newBorder;

@SuppressWarnings("unused")
public class ProductionUiProfileFactory implements UIProfileFactory {

	@Override
	public UIProfile getUIProfile() {
		UIProfile profile = new UIProfile();

		profile.addStyle(
				UISelectorFactory.forShape(HexagonShape.class),
				allUndefined().withBorder(
						newBorder(
								Color.BLACK,
								1
						)
				)
						.build()
		);

		profile.addStyle(
				UISelectorFactory.forName("2ndLayerOfHexagons"),
				allUndefined().withBorder(
						newBorder(
								Color.BLACK,
								1
						)
				)
						.build()
		);

		return profile;
	}
}
