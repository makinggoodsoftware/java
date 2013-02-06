package com.mgs.fantasi.wireframeTreeBuilders;

import com.mgs.fantasi.properties.UIPropertyType;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContentFactory;
import com.mgs.tree.Branch;
import com.mgs.tree.Tree;

import static com.mgs.fantasi.properties.UIPropertyFactory.uiProperty;

public class PolygonWireframeTreeBuilder extends BaseWireframeTreeBuilder<PolygonWireframeTreeBuilder> {
	public PolygonWireframeTreeBuilder(PolygonPointsIterator polygonPointsIterator) {
		getUiPropertiesBuilder().withShape(uiProperty(polygonPointsIterator, UIPropertyType.SHAPE));
	}

	@Override
	public Tree<Wireframe, CollocationInfo> build(WireframeContentFactory wireframeContentFactory) {
		Wireframe wireframe = new Wireframe(this.getClass(), getName(), getUiPropertiesBuilder().build());
		Branch<Wireframe, CollocationInfo> wireframeCollocationInfoBranch = new Branch<Wireframe, CollocationInfo>(wireframeContentFactory.getEmptyConnectionManager());

		return new Tree<Wireframe, CollocationInfo>(wireframe, wireframeCollocationInfoBranch);
	}
}
