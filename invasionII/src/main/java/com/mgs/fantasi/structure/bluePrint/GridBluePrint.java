package com.mgs.fantasi.structure.bluePrint;

import com.mgs.fantasi.properties.data.measurements.Fraction;
import com.mgs.fantasi.structure.CollocationInfo;
import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.wireframe.Wireframe;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static com.mgs.fantasi.properties.data.measurements.Fractions.all;
import static com.mgs.fantasi.structure.Structures.gridStructure;

public class GridBluePrint implements BluePrint {
	private final Wireframe wireframe;
	private final String name;
	private Dimension dimension;
	private Map<Point, CoordinateContent> cellContents;

	public GridBluePrint(String name, Wireframe wireframe) {
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
	public Structure buildStructure() {
		Structure structure = gridStructure(wireframe, getName(), this.getClass());

		for (int x = 0; x < dimension.width; x++) {
			for (int y = 0; y < dimension.height; y++) {
				CoordinateContent child = locateContentFor(x, y);
				Fraction width = child.getWidthSizeRatio();
				Fraction height = child.getHeightSizeRatio();
				CollocationInfo collocationInfo = new CollocationInfo(0, width, height, x, y);
				structure.addChild(collocationInfo, child.getContent().buildStructure());
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
		private final GridBluePrint parent;
		private final Dimension dimension;

		private final Map<Point, CoordinateContent> cellContents = new HashMap<Point, CoordinateContent>();

		public GridWireframeContent(GridBluePrint parent, Dimension dimension) {
			this.parent = parent;
			this.dimension = dimension;
		}

		public GridWireframeContent withCell(Point point, Fraction heightSizeRatio, Fraction widthSizeRatio, BluePrint content) {
			cellContents.put(point, new CoordinateContent(heightSizeRatio, widthSizeRatio, content));
			return this;
		}

		public Structure build() {
			return fill().buildStructure();
		}

		public GridBluePrint fill() {
			parent.setCellContent(cellContents);
			return parent;
		}

		public GridBluePrint allCellsWith(BluePrint content) {
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
		private final BluePrint content;

		public CoordinateContent(Fraction heightSizeRatio, Fraction widthSizeRatio, BluePrint content) {
			this.heightSizeRatio = heightSizeRatio;
			this.widthSizeRatio = widthSizeRatio;
			this.content = content;
		}

		public BluePrint getContent() {
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
