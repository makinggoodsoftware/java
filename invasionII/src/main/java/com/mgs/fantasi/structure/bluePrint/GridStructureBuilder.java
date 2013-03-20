package com.mgs.fantasi.structure.bluePrint;

import com.mgs.fantasi.properties.data.measurements.Fraction;
import com.mgs.fantasi.structure.CollocationInfo;
import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.structure.StructureBuilderFactory;
import com.mgs.fantasi.structure.bluePrintPatterns.StructureContentBuilder;
import com.mgs.fantasi.wireframe.Wireframe;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static com.mgs.fantasi.properties.data.measurements.Fractions.all;
import static com.mgs.fantasi.structure.Structures.gridStructure;

public class GridStructureBuilder {
	private Dimension dimension;
	private Map<Point, CoordinateContent> cellContents;

	public GridWireframeContent withDimension(Dimension dimension) {
		this.dimension = dimension;
		return new GridWireframeContent(this, dimension);
	}

	public Structure buildStructure(String name, Wireframe wireframe, Class<? extends StructureContentBuilder> parent) {
		Structure structure = gridStructure(wireframe, name, parent);

		for (int x = 0; x < dimension.width; x++) {
			for (int y = 0; y < dimension.height; y++) {
				CoordinateContent child = locateContentFor(x, y);
				Fraction width = child.getWidthSizeRatio();
				Fraction height = child.getHeightSizeRatio();
				CollocationInfo collocationInfo = new CollocationInfo(0, width, height, x, y);
				structure.addChild(collocationInfo, child.getContent().build());
			}
		}

		return structure;
	}

	private CoordinateContent locateContentFor(int x, int y) {
		return cellContents.get(new Point(x, y));
	}

	private void setCellContent(Map<Point, CoordinateContent> cellContents) {
		this.cellContents = cellContents;
	}

	public static class GridWireframeContent {
		private final GridStructureBuilder parent;
		private final Dimension dimension;

		private final Map<Point, CoordinateContent> cellContents = new HashMap<Point, CoordinateContent>();

		public GridWireframeContent(GridStructureBuilder parent, Dimension dimension) {
			this.parent = parent;
			this.dimension = dimension;
		}

		public GridWireframeContent withCell(Point point, Fraction heightSizeRatio, Fraction widthSizeRatio, StructureBuilderFactory.StructureBuilder content) {
			cellContents.put(point, new CoordinateContent(heightSizeRatio, widthSizeRatio, content));
			return this;
		}

		public Structure build(String name, Wireframe wireframe, Class<? extends StructureContentBuilder> parent) {
			return fill().buildStructure(name, wireframe, parent);
		}

		public GridStructureBuilder fill() {
			parent.setCellContent(cellContents);
			return parent;
		}

		public GridStructureBuilder allCellsWith(StructureBuilderFactory.StructureBuilder content) {
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
		private final StructureBuilderFactory.StructureBuilder content;

		public CoordinateContent(Fraction heightSizeRatio, Fraction widthSizeRatio, StructureBuilderFactory.StructureBuilder content) {
			this.heightSizeRatio = heightSizeRatio;
			this.widthSizeRatio = widthSizeRatio;
			this.content = content;
		}

		public StructureBuilderFactory.StructureBuilder getContent() {
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
