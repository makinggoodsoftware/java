package com.mgs.fantasi.wireframe;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.properties.data.polygon.NativeRectanguarShape;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;

import static com.mgs.fantasi.properties.UIPropertiesBuilderFactory.allEmptyUIProperties;

public class Wireframes {
	public static Wireframe rectangle(UIPropertiesBuilder uiPropertiesBuilder) {
		return new Wireframe(uiPropertiesBuilder.build(), new NativeRectanguarShape());
	}

	public static Wireframe newWireframe(UIPropertiesBuilder uiPropertiesBuilder, PolygonPointsIterator shape) {
		return new Wireframe(uiPropertiesBuilder.build(), shape);
	}

	public static Wireframe emptyRectangle() {
		return new Wireframe(allEmptyUIProperties().build(), new NativeRectanguarShape());
	}
}
