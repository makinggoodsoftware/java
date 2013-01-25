package com.mgs.invasion;

import com.mgs.fantasi.driver.UIDriver;
import com.mgs.fantasi.properties.measurements.Measurement;
import com.mgs.fantasi.properties.measurements.Measurements;
import com.mgs.fantasi.styles.UIProfileFactory;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContentFactory;
import com.mgs.fantasi.wireframeTreeBuilders.WireframeTreeBuilder;
import com.mgs.invasion.mvc.views.DebugUIProfileFactory;
import com.mgs.tree.Tree;

import javax.swing.*;
import java.awt.*;

import static com.mgs.fantasi.wireframe.Wireframes.layered;
import static com.mgs.fantasi.wireframe.Wireframes.rectangle;
import static com.mgs.invasion.mvc.views.HexagonRowsWireframeTreeBuilder.hexagonRows;

public class Main {
	private final WireframeContentFactory wireframeContentFactory = new WireframeContentFactory();

	public static void main(String... args) {
		new Main().go(new DebugUIProfileFactory());
	}

	private void go(UIProfileFactory uiProfileFactory) {
		UIDriver<JPanel> uiDriver = UIDriver.forSwing();

		int numberOVerticalDivisions = 10;
		int numberOfGenerations = 5;


		Measurement hexagonMeasurement = Measurements.futureMeasurement();
		WireframeTreeBuilder wireframeTreeBuilder = layered().
				withLayer(
						hexagonRows(numberOVerticalDivisions, numberOfGenerations).
								withName("OddHexagonRows").
								withHexagonMeasurement(hexagonMeasurement)
				).
				withLayer(
						rectangle().
								withPadding(hexagonMeasurement.asPadding().withHalfOfItsSize()).
								withName("EvenHexagonRows").
								withContent(
										hexagonRows(numberOVerticalDivisions, numberOfGenerations).
												withHexagonMeasurement(hexagonMeasurement)
								)
				);
		Tree<Wireframe, CollocationInfo> tree = wireframeTreeBuilder.build(wireframeContentFactory);
		uiDriver.show(tree, new Dimension(400, 400), uiProfileFactory.getUIProfile());
	}

}
