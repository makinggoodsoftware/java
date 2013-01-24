package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.measurements.Fraction;
import com.mgs.fantasi.properties.measurements.Fractions;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContentFactory;
import com.mgs.tree.Branch;
import com.mgs.tree.Tree;

import java.awt.*;

public class PijamaRowsWireframeTreeBuilder extends BaseWireframeTreeBuilder<PijamaRowsWireframeTreeBuilder> {
	private static final int UNDEFINED = -1;
	private final TwoLinesWireframeTreeBuilder generationBuilder;
	private int numberOfGenerations = UNDEFINED;

	public PijamaRowsWireframeTreeBuilder(WireframeTreeBuilder firstRowTreeBuilder, WireframeTreeBuilder secondRowTreeBuilder) {
		generationBuilder = new TwoLinesWireframeTreeBuilder(firstRowTreeBuilder, secondRowTreeBuilder);
	}

	public PijamaRowsWireframeTreeBuilder withFirstRowSize(Fraction sizeConstraints) {
		generationBuilder.withFirstRowSize(sizeConstraints);
		return this;
	}

	public PijamaRowsWireframeTreeBuilder withNumberOfGenerations(int numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
		return this;
	}

	@Override
	public Tree<Wireframe, CollocationInfo> build(final WireframeContentFactory wireframeContentFactory) {
		Dimension dimension = new Dimension(1, numberOfGenerations);
		Branch<Wireframe, CollocationInfo> wireframeBranch = new Branch<Wireframe, CollocationInfo>(wireframeContentFactory.getGridConnectionManager());
		for (int x = 0; x < dimension.width; x++) {
			for (int y = 0; y < dimension.height; y++) {
				Tree<Wireframe, CollocationInfo> content = generationBuilder.build(wireframeContentFactory);
				CollocationInfo collocationInfo = new CollocationInfo(0, Fractions.all(), Fractions.all(), x, y);
				wireframeBranch.addChild(collocationInfo, content);
			}
		}
		Wireframe wireframe = new Wireframe(this.getClass(), getName(), getUiProperties());
		return new Tree<Wireframe, CollocationInfo>(wireframe, wireframeBranch);
	}
}
