package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.properties.data.polygon.NativeRectanguarShape;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.wireframe.tree.builder.LayeredElementsWireframeTreeBuilder;
import com.mgs.fantasi.wireframe.tree.builder.SingleChildWireframeTreeBuilder;
import com.mgs.fantasi.wireframe.tree.builder.VerticalSlicesWireframeTreeBuilder;
import com.mgs.fantasi.wireframe.tree.builder.WireframeTreeBuilder;

public class Wireframes {
	public static LayeredElementsWireframeTreeBuilder layered(String name, UIPropertiesBuilder uiPropertiesBuilder) {
		return new LayeredElementsWireframeTreeBuilder(name, new NativeRectanguarShape(), uiPropertiesBuilder);
	}

	public static SingleChildWireframeTreeBuilder rectangle(String name, PolygonPointsIterator shape, UIPropertiesBuilder uiPropertiesBuilder) {
		return new SingleChildWireframeTreeBuilder(name, shape, uiPropertiesBuilder);
	}

	public static VerticalSlicesWireframeTreeBuilder verticalSlices(String name, WireframeTreeBuilder contentTreeBuilder, UIPropertiesBuilder uiPropertiesBuilder) {
		return new VerticalSlicesWireframeTreeBuilder(name, contentTreeBuilder, new NativeRectanguarShape(), uiPropertiesBuilder);
	}

}
