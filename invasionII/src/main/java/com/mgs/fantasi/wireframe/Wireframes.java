package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;
import com.mgs.fantasi.views.*;

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

	public static PijamaRowsWireframeTreeBuilder pijamaRows(WireframeTreeBuilder firstRowTreeBuilder, WireframeTreeBuilder secondRowTreeBuilder) {
		return new PijamaRowsWireframeTreeBuilder(firstRowTreeBuilder, secondRowTreeBuilder);
	}
}
