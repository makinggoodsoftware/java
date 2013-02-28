package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.wireframe.tree.builder.*;

public class Wireframes {
	public static LayeredElementsWireframeTreeBuilder layered(String name, UIPropertiesBuilder uiPropertiesBuilder) {
		return new LayeredElementsWireframeTreeBuilder(uiPropertiesBuilder, name);
	}

	public static RectangleWireframeTreeBuilder rectangle(String name, UIPropertiesBuilder uiPropertiesBuilder) {
		return new RectangleWireframeTreeBuilder(name, uiPropertiesBuilder);
	}

	public static PolygonWireframeTreeBuilder polygon(String name, PolygonPointsIterator polygonPointsIterator, UIPropertiesBuilder uiPropertiesBuilder) {
		return new PolygonWireframeTreeBuilder(name, polygonPointsIterator, uiPropertiesBuilder);
	}

	public static VerticalSlicesWireframeTreeBuilder verticalSlices(String name, WireframeTreeBuilder contentTreeBuilder, UIPropertiesBuilder uiPropertiesBuilder) {
		return new VerticalSlicesWireframeTreeBuilder(name, contentTreeBuilder, uiPropertiesBuilder);
	}

	public static PijamaRowsWireframeTreeBuilder pijamaRows(String name, WireframeTreeBuilder oddLineTreeBuilder, WireframeTreeBuilder evenRowTreeBuilder, UIPropertiesBuilder uiPropertiesBuilder) {
		return new PijamaRowsWireframeTreeBuilder(name, oddLineTreeBuilder, evenRowTreeBuilder, uiPropertiesBuilder);
	}

	public static TwoLinesWireframeTreeBuilder twoLines(String name, WireframeTreeBuilder firstLineTreeBuilder, WireframeTreeBuilder secondLineTreeBuilder, UIPropertiesBuilder uiPropertiesBuilder) {
		return new TwoLinesWireframeTreeBuilder(name, firstLineTreeBuilder, secondLineTreeBuilder, uiPropertiesBuilder);
	}
}
