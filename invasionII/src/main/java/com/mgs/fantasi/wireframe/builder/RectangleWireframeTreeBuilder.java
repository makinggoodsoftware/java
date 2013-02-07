package com.mgs.fantasi.wireframe.builder;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContentFactory;
import com.mgs.tree.Branch;
import com.mgs.tree.Tree;

import static com.mgs.fantasi.properties.data.measurements.Fractions.all;

public class RectangleWireframeTreeBuilder extends BaseWireframeTreeBuilder<RectangleWireframeTreeBuilder> {
	private WireframeTreeBuilder content;

	public RectangleWireframeTreeBuilder() {
	}

	public RectangleWireframeTreeBuilder withContent(WireframeTreeBuilder content) {
		this.content = content;
		return this;
	}

	@Override
	public Tree<Wireframe, CollocationInfo> build(WireframeContentFactory wireframeContentFactory) {
		Wireframe wireframe = new Wireframe(this.getClass(), getName(), getUiPropertiesBuilder().build());
		if (content == null) {
			Branch<Wireframe, CollocationInfo> wireframeCollocationInfoBranch = new Branch<Wireframe, CollocationInfo>(wireframeContentFactory.getEmptyConnectionManager());
			return new Tree<Wireframe, CollocationInfo>(wireframe, wireframeCollocationInfoBranch);
		}
		Branch<Wireframe, CollocationInfo> wireframeBranch = new Branch<Wireframe, CollocationInfo>(wireframeContentFactory.getRectangleConnectionManager());
		wireframeBranch.addChild(new CollocationInfo(0, all(), all(), 0, 0), content.build(wireframeContentFactory));
		return new Tree<Wireframe, CollocationInfo>(wireframe, wireframeBranch);
	}
}