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

		StructureBuilder hexagon = new PolygonStructureBuilder(new HexagonShape()).
				withSizeStrategy(new ExpandToParent());
		RectangleStructureBuilder rectangle = new RectangleStructureBuilder().
				witchContent
						(
								hexagon
						);
		VerticalSlicesStructureBuilder firstRowBuilder = new VerticalSlicesStructureBuilder(
				rectangle
		).withVerticalDivisions(6);
		Wireframe content =
			new PijamaRowsStructureBuilder(
					firstRowBuilder,
				new RectangleStructureBuilder()
			).withFirstRowSize(FractionsOfThree.thwoThirds()).
			withNumberOfGerations(3).
			build();

		uiDriver.show(hexagon.build(), new Dimension(400,400));
	}

}
