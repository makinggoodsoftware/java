package com.mgs.invasion.mvc.views;

import com.mgs.fantasi.profile.UIProfile;
import com.mgs.fantasi.properties.data.polygon.HexagonShape;
import com.mgs.fantasi.selectors.UISelectorFactory;
import com.mgs.fantasi.structure.structureBuilder.Layout.EmptyLayout;

import java.awt.*;

import static com.mgs.fantasi.properties.UIPropertiesBuilderFactory.allUndefinedUIProperties;
import static com.mgs.fantasi.properties.UIPropertyFactory.backgroundColorFromAwt;
import static com.mgs.fantasi.properties.UIPropertyFactory.newBorder;
import static java.awt.Color.GREEN;
import static java.awt.Color.RED;

public class ProfileFactory {
	public UIProfile debug() {
		UIProfile debugProfile = new UIProfile();

		debugProfile.addStyle(
				UISelectorFactory.forType(EmptyLayout.class),
				allUndefinedUIProperties().
						withBorder(newBorder(
								Color.BLUE,
								5
						)).
						withBackgroundColor(backgroundColorFromAwt(GREEN))
						.build()
		);
		debugProfile.addStyle(
				UISelectorFactory.forShape(HexagonShape.class),
				allUndefinedUIProperties().
						withBorder(newBorder(
								Color.YELLOW,
								10
						)).
						withBackgroundColor(backgroundColorFromAwt(RED))
						.build()
		);

		return debugProfile;
	}

	public UIProfile production() {
		UIProfile profile = new UIProfile();

		profile.addStyle(
				UISelectorFactory.forShape(HexagonShape.class),
				allUndefinedUIProperties().withBorder(
						newBorder(
								Color.BLACK,
								1
						)
				)
						.build()
		);

		profile.addStyle(
				UISelectorFactory.forName("2ndLayerOfHexagons"),
				allUndefinedUIProperties().withBorder(
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
