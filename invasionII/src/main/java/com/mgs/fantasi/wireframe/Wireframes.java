package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.wireframe.tree.builder.*;

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

	public static VerticalSlicesWireframeTreeBuilder verticalSlices(WireframeTreeBuilderOld contentTreeBuilder) {
		return new VerticalSlicesWireframeTreeBuilder(contentTreeBuilder);
	}

	public static PijamaRowsWireframeTreeBuilder pijamaRows(WireframeTreeBuilderOld oddLineTreeBuilder, WireframeTreeBuilderOld evenRowTreeBuilder) {
		return new PijamaRowsWireframeTreeBuilder(oddLineTreeBuilder, evenRowTreeBuilder);
	}

	public static TwoLinesWireframeTreeBuilder twoLines(WireframeTreeBuilderOld firstLineTreeBuilder, WireframeTreeBuilderOld secondLineTreeBuilder) {
		return new TwoLinesWireframeTreeBuilder(firstLineTreeBuilder, secondLineTreeBuilder);
	}
}
