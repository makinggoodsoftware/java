package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.wireframe.builder.*;

public class Wireframes {
	public static LayeredElementsWireframeTreeBuilder layered() {
		return new LayeredElementsWireframeTreeBuilder();
	}

	public static RectangleWireframeTreeBuilder rectangle() {
		return new RectangleWireframeTreeBuilder();
	}

	public static PolygonWireframeTreeBuilder polygon(PolygonPointsIterator polygonPointsIterator) {
		return new PolygonWireframeTreeBuilder(polygonPointsIterator);
	}

	public static VerticalSlicesWireframeTreeBuilder verticalSlices(WireframeTreeBuilder contentTreeBuilder) {
		return new VerticalSlicesWireframeTreeBuilder(contentTreeBuilder);
	}

	public static PijamaRowsWireframeTreeBuilder pijamaRows(WireframeTreeBuilder oddLineTreeBuilder, WireframeTreeBuilder evenRowTreeBuilder) {
		return new PijamaRowsWireframeTreeBuilder(oddLineTreeBuilder, evenRowTreeBuilder);
	}

	public static TwoLinesWireframeTreeBuilder twoLines(WireframeTreeBuilder firstLineTreeBuilder, WireframeTreeBuilder secondLineTreeBuilder) {
		return new TwoLinesWireframeTreeBuilder(firstLineTreeBuilder, secondLineTreeBuilder);
	}
}
