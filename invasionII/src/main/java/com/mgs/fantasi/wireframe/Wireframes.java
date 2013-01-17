package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;
import com.mgs.fantasi.views.*;

public class Wireframes {
	public static LayeredElementsWireframeBuilder layered() {
		return new LayeredElementsWireframeBuilder();
	}

	public static RectangleWireframeBuilder rectangle() {
		return new RectangleWireframeBuilder();
	}

	public static PolygonWireframeBuilder polygon(PolygonPointsIterator polygonPointsIterator) {
		return new PolygonWireframeBuilder(polygonPointsIterator);
	}

	public static VerticalSlicesWireframeBuilder verticalSlices(WireframeBuilder contentBuilder) {
		return new VerticalSlicesWireframeBuilder(contentBuilder);
	}

	public static PijamaRowsWireframeBuilder pijamaRows(WireframeBuilder firstRowBuilder, WireframeBuilder secondRowBuilder) {
		return new PijamaRowsWireframeBuilder(firstRowBuilder, secondRowBuilder);
	}
}
