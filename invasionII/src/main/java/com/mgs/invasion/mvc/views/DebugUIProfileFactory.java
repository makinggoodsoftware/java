package com.mgs.invasion.mvc.views;

import com.mgs.fantasi.properties.polygon.HexagonShape;
import com.mgs.fantasi.selectors.UISelectorFactory;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.styles.UIProfileFactory;
import com.mgs.fantasi.styles.UIStyle;
import com.mgs.fantasi.wireframeTreeBuilders.RectangleWireframeTreeBuilder;

import java.awt.*;

import static com.mgs.fantasi.properties.UIPropertyFactory.backgroundColorFromAwt;
import static com.mgs.fantasi.properties.UIPropertyFactory.newBorder;
import static java.awt.Color.GREEN;
import static java.awt.Color.RED;

public class DebugUIProfileFactory implements UIProfileFactory {
	public DebugUIProfileFactory() {
	}

	@Override
	public UIProfile getUIProfile() {
		UIProfile debugProfile = new UIProfile();

		debugProfile.addStyle(
				UISelectorFactory.forType(RectangleWireframeTreeBuilder.class),
				new UIStyle().
						withBorder(newBorder(
								Color.BLUE,
								5
						)).
						withBackgroundColor(backgroundColorFromAwt(GREEN))
		);
		debugProfile.addStyle(
				UISelectorFactory.forShape(HexagonShape.class),
				new UIStyle().
						withBorder(newBorder(
								Color.YELLOW,
								10
						)).
						withBackgroundColor(backgroundColorFromAwt(RED))
		);

		return debugProfile;
	}
}
