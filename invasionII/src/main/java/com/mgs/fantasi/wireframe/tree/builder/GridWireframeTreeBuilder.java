package com.mgs.fantasi.wireframe.tree.builder;

import com.mgs.fantasi.properties.data.measurements.Fraction;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.tree.WireframeTree;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static com.mgs.fantasi.properties.data.measurements.Fractions.all;
import static com.mgs.fantasi.wireframe.tree.WireframeTreeFactory.grid;

public class GridWireframeTreeBuilder implements WireframeTreeBuilder {
	private final Wireframe wireframe;
	private final String name;
	private Dimension dimension;
	private Map<Point, CoordinateContent> cellContents;

	public GridWireframeTreeBuilder(String name, Wireframe wireframe) {
		this.name = name;
		this.wireframe = wireframe;
	}

	public GridWireframeContent withDimension(Dimension dimension) {
		this.dimension = dimension;
		return new GridWireframeContent(this, dimension);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public WireframeTree build() {
		WireframeTree wireframeTree = grid(wireframe, getName(), this.getClass());

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

	@Override
	public Wireframe getRootWireframe() {
		return wireframe;
	}

	private CoordinateContent locateContentFor(int x, int y) {
		return cellContents.get(new Point(x, y));
	}

	private void setCellContent(Map<Point, CoordinateContent> cellContents) {
		this.cellContents = cellContents;
	}

	public static class GridWireframeContent {
		private final GridWireframeTreeBuilder parent;
		private final Dimension dimension;

		private final Map<Point, CoordinateContent> cellContents = new HashMap<Point, CoordinateContent>();

		public GridWireframeContent(GridWireframeTreeBuilder parent, Dimension dimension) {
			this.parent = parent;
			this.dimension = dimension;
		}

		public GridWireframeContent withCell(Point point, Fraction heightSizeRatio, Fraction widthSizeRatio, WireframeTreeBuilder content) {
			cellContents.put(point, new CoordinateContent(heightSizeRatio, widthSizeRatio, content));
			return this;
		}

		public WireframeTree build() {
			return fill().build();
		}

		public GridWireframeTreeBuilder fill() {
			parent.setCellContent(cellContents);
			return parent;
		}

		public GridWireframeTreeBuilder allCellsWith(WireframeTreeBuilder content) {
			for (int x = 0; x < dimension.width; x++) {
				for (int y = 0; y < dimension.height; y++) {
					cellContents.put(new Point(x, y), new CoordinateContent(all(), all(), content));
				}
			}
			return fill();
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
