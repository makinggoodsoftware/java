package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.properties.data.polygon.NativeRectanguarShape;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.wireframe.tree.builder.FinalWireframeTreeBuilder;
import com.mgs.fantasi.wireframe.tree.builder.LayeredElementsWireframeTreeBuilder;
import com.mgs.fantasi.wireframe.tree.builder.SingleChildWireframeTreeBuilder;
import com.mgs.fantasi.wireframe.tree.builder.WireframeTreeBuilder;

public class Wireframes {
	public static LayeredElementsWireframeTreeBuilder layered(String name, UIPropertiesBuilder uiPropertiesBuilder) {
		return new LayeredElementsWireframeTreeBuilder(name, new NativeRectanguarShape(), uiPropertiesBuilder);
	}

	public static SingleChildWireframeTreeBuilder rectangle(String name, WireframeTreeBuilder content, PolygonPointsIterator shape, UIPropertiesBuilder uiPropertiesBuilder) {
		return new SingleChildWireframeTreeBuilder(name, content, shape, uiPropertiesBuilder);
	}

	public static FinalWireframeTreeBuilder emptyRectangle(String name, PolygonPointsIterator shape, UIPropertiesBuilder uiPropertiesBuilder) {
		return new FinalWireframeTreeBuilder(name, shape, uiPropertiesBuilder);
	}

}
