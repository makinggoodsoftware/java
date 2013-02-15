package com.mgs.fantasi.wireframe.tree.builder;

import com.mgs.fantasi.properties.data.measurements.Fraction;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.tree.WireframeTree;

import java.awt.*;

import static com.mgs.fantasi.wireframe.tree.WireframeTreeFactory.grid;

public class TwoLinesWireframeTreeBuilder extends BaseWireframeTreeBuilder {
	private final WireframeTreeBuilderOld firstLineTreeBuilder;
	private final WireframeTreeBuilderOld secondLineTreeBuilder;

	private Fraction firstLineHeightSizeRatio = null;

	public TwoLinesWireframeTreeBuilder(WireframeTreeBuilderOld firstLineTreeBuilder, WireframeTreeBuilderOld secondLineTreeBuilder) {
		this.firstLineTreeBuilder = firstLineTreeBuilder;
		this.secondLineTreeBuilder = secondLineTreeBuilder;
	}

	public TwoLinesWireframeTreeBuilder withFirstRowSize(Fraction firstLineHeightSizeRatio) {
		this.firstLineHeightSizeRatio = firstLineHeightSizeRatio;
		return this;
	}

	@Override
	public WireframeTree build() {
		Wireframe wireframe = new Wireframe(getUiPropertiesBuilder().build());
		WireframeTree wireframeTree = grid(wireframe, getName(), this.getClass());

		Dimension dimension = new Dimension(1, 2);
		Fraction remainder = Fractions.allWithBase(firstLineHeightSizeRatio.getBase()).minus(firstLineHeightSizeRatio);

		for (int x = 0; x < dimension.width; x++) {
			for (int y = 0; y < dimension.height; y++) {
				CollocationInfo collocationInfo;
				WireframeTree child;
				if (y == 0) {
					collocationInfo = new CollocationInfo(0, Fractions.all(), firstLineHeightSizeRatio, 0, y);
					child = firstLineTreeBuilder.build();
				} else {
					collocationInfo = new CollocationInfo(0, Fractions.all(), remainder, 0, y);
					child = secondLineTreeBuilder.build();
				}
				wireframeTree.addChild(collocationInfo, child);
			}
		}

		return wireframeTree;
	}
}
