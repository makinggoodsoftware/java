package com.mgs.fantasi.views;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContentFactory;
import com.mgs.tree.Branch;
import com.mgs.tree.Tree;

import java.awt.*;

import static com.mgs.fantasi.properties.measurements.Fractions.all;

public class VerticalSlicesWireframeTreeBuilder extends BaseWireframeTreeBuilder {

	private static final int UNDEFINED = -1;
	private final WireframeTreeBuilder contentTreeBuilder;
	private int numberOfDivisions = UNDEFINED;

	public VerticalSlicesWireframeTreeBuilder(WireframeTreeBuilder contentTreeBuilder) {
		this.contentTreeBuilder = contentTreeBuilder;
	}

	@Override
	public Tree<Wireframe, CollocationInfo> build(final WireframeContentFactory wireframeContentFactory) {
		Dimension dimension = new Dimension(numberOfDivisions, 1);
		Branch<Wireframe, CollocationInfo> wireframeBranch = new Branch<Wireframe, CollocationInfo>(wireframeContentFactory.getGridConnectionManager());
		for (int x = 0; x < dimension.width; x++) {
			for (int y = 0; y < dimension.height; y++) {
				CollocationInfo collocationInfo = new CollocationInfo(0, all(), all(), x, y);
				Tree<Wireframe, CollocationInfo> child = contentTreeBuilder.build(wireframeContentFactory);
				wireframeBranch.addChild(collocationInfo, child);
			}
		}
		Wireframe wireframe = new Wireframe(this.getClass(), getName(), getUiProperties());
		return new Tree<Wireframe, CollocationInfo>(wireframe, wireframeBranch);
	}

	public VerticalSlicesWireframeTreeBuilder withVerticalDivisions(int numberOVerticalDivisions) {
		this.numberOfDivisions = numberOVerticalDivisions;
		return this;
	}
}
