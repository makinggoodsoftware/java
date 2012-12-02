package com.mgs.invasion;

import com.mgs.fantasi.polygon.HexagonShape;
import com.mgs.fantasi.structures.*;
import com.mgs.fantasi.ui.driver.UIDriver;
import com.mgs.fantasi.ui.profile.ExpandToParent;
import com.mgs.fantasi.ui.profile.UIProfileFactory;
import com.mgs.fantasi.ui.wireframe.Wireframe;
import com.mgs.invasion.mvc.view.ProductionUiProfileFactory;

import javax.swing.*;
import java.awt.*;

public class Main {
	public static void main (String ... args){
//		new Main().go(new DebugUIProfileFactory());
		new Main().go(new ProductionUiProfileFactory());
	}

	private void go(UIProfileFactory uiProfileFactory) {
		UIDriver<JPanel> uiDriver = UIDriver.forSwing(uiProfileFactory.getUIProfile());

		Wireframe content =
			new PijamaRowsStructureBuilder(
				new VerticalSlicesStructureBuilder(
					new RectangleStructureBuilder().
					witchContent
							(
									new PolygonStructureBuilder(new HexagonShape()).
											withSizeStrategy(new ExpandToParent())
							)
				).withVerticalDivisions(6),
				new RectangleStructureBuilder()
			).withFirstRowSize(Fractions.thwoThirds()).
			withNumberOfGerations(3).
			build();

		uiDriver.show(content, new Dimension(400,400));
	}

}
