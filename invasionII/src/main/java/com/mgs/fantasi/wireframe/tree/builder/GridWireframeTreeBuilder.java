package com.mgs.fantasi.wireframe.tree.builder;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.properties.data.measurements.Fraction;
import com.mgs.fantasi.properties.data.polygon.NativeRectanguarShape;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.tree.WireframeTree;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static com.mgs.fantasi.wireframe.tree.WireframeTreeFactory.grid;

public class GridWireframeTreeBuilder implements WireframeTreeBuilder {
	private final UIPropertiesBuilder uiPropertiesBuilder;
	private final String name;
	private Dimension dimension;
	private Map<Point, CoordinateContent> cellContents;

	public GridWireframeTreeBuilder(String name, UIPropertiesBuilder uiPropertiesBuilder) {
		this.name = name;
		this.uiPropertiesBuilder = uiPropertiesBuilder;
	}

	public GridWireframeContent withDimension(Dimension dimension) {
		this.dimension = dimension;
		return new GridWireframeContent(this);
	}

	@Override
	public UIPropertiesBuilder getUiPropertiesBuilder() {
		return uiPropertiesBuilder;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public WireframeTree build() {
		Wireframe wireframe = new Wireframe(getUiPropertiesBuilder().build(), new NativeRectanguarShape());
		WireframeTree wireframeTree = grid(wireframe, getName(), this.getClass());

		Dimension dimension = new Dimension(1, 2);

		for (int x = 0; x < dimension.width; x++) {
			for (int y = 0; y < dimension.height; y++) {
				CoordinateContent child = locateContentFor(x, y);
				Fraction width = child.getWidthSizeRatio();
				Fraction height = child.getHeightSizeRatio();
				CollocationInfo collocationInfo = new CollocationInfo(0, width, height, x, y);
				wireframeTree.addChild(collocationInfo, child.getContent().build());
			}
		}

		return wireframeTree;
	}

	private CoordinateContent locateContentFor(int x, int y) {
		return cellContents.get(new Point(x, y));
	}

	private void setCellContent(Map<Point, CoordinateContent> cellContents) {
		this.cellContents = cellContents;
	}

	public static class GridWireframeContent {
		private final GridWireframeTreeBuilder parent;

		private final Map<Point, CoordinateContent> cellContents = new HashMap<Point, CoordinateContent>();

		public GridWireframeContent(GridWireframeTreeBuilder parent) {
			this.parent = parent;
		}

		public GridWireframeContent withCell(Point point, Fraction heightSizeRatio, Fraction widthSizeRatio, WireframeTreeBuilder content) {
			cellContents.put(point, new CoordinateContent(heightSizeRatio, widthSizeRatio, content));
			return this;
		}

		public WireframeTree build() {
			parent.setCellContent(cellContents);
			return parent.build();
		}

	}

	public static class CoordinateContent {
		private final Fraction heightSizeRatio;
		private final Fraction widthSizeRatio;
		private final WireframeTreeBuilder content;

		public CoordinateContent(Fraction heightSizeRatio, Fraction widthSizeRatio, WireframeTreeBuilder content) {
			this.heightSizeRatio = heightSizeRatio;
			this.widthSizeRatio = widthSizeRatio;
			this.content = content;
		}

		public WireframeTreeBuilder getContent() {
			return content;
		}

		public Fraction getHeightSizeRatio() {
			return heightSizeRatio;
		}

		public Fraction getWidthSizeRatio() {
			return widthSizeRatio;
		}
	}
}
