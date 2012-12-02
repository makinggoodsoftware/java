package com.mgs.invasion;

import com.mgs.fantasi.polygon.HexagonShape;
import com.mgs.fantasi.structures.*;
import com.mgs.fantasi.ui.driver.UIDriver;
import com.mgs.fantasi.ui.profile.ExpandToParent;
import com.mgs.fantasi.ui.profile.UIProfileFactory;
import com.mgs.fantasi.ui.wireframe.Wireframe;
import com.mgs.invasion.mvc.view.DebugUIProfileFactory;

import javax.swing.*;
import java.awt.*;

public class Main {
	public static void main (String ... args){
		new Main().go(new DebugUIProfileFactory());
	}

	private void go(UIProfileFactory uiProfileFactory) {
		UIDriver<JPanel> uiDriver = UIDriver.forSwing(uiProfileFactory.getUIProfile());

		StructureBuilder hexagon = new PolygonStructureBuilder(new HexagonShape()).
				withSizeStrategy(new ExpandToParent());
		Wireframe content =
			new PijamaRowsStructureBuilder(
				new VerticalSlicesStructureBuilder(
					new RectangleStructureBuilder().
					witchContent
					(
							hexagon
					)
				).withVerticalDivisions(6),
				new RectangleStructureBuilder()
			).withFirstRowSize(FractionsOfThree.thwoThirds()).
			withNumberOfGerations(3).
			build();

		uiDriver.show(hexagon.build(), new Dimension(400,400));
	}

}
