package com.mgs.fantasi.structures;

import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.ui.profile.SizeStrategy;
import com.mgs.fantasi.ui.wireframe.CellContentGenerator;
import com.mgs.fantasi.ui.wireframe.Grid;
import com.mgs.fantasi.ui.wireframe.Wireframe;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public abstract class BaseStructureBuilder implements StructureBuilder{
	PolygonPointsIterator shape = new NativeRectanguarShape();
	private SizeStrategy sizeStrategy = new NativeDefaultSizeStrategy();

	@Override
	public final Wireframe build() {
		Grid<Wireframe> content = buildLayout();
		content.fillCells(new CellContentGenerator<Wireframe>(){
			@Override
			public Wireframe generateContentFor(int x, int y) {
				return BaseStructureBuilder.this.generateContentFor(x, y);
			}
		});
		if (content == null) throw new RuntimeException("Content can't be null, needs to be at lease GridFactory.empty()");
		return new Wireframe(getClass(), shape, content, sizeStrategy);
	}

	protected abstract Wireframe generateContentFor(int x, int y);

	public final BaseStructureBuilder withShape (PolygonPointsIterator shape){
		this.shape = shape;
		return this;
	}

	public StructureBuilder withSizeStrategy(SizeStrategy sizeStrategy) {
		this.sizeStrategy = sizeStrategy;
		return this;
	}

	protected abstract Grid<Wireframe> buildLayout();

	private class NativeRectanguarShape implements PolygonPointsIterator {
		@Override
		public boolean isRectangular() {
			return true;
		}

		@Override
		public List<Point2D.Double> getPointListFromBottomLeftCorner(Dimension size) {
			throw new NotImplementedException();
		}

	}

	private class NativeDefaultSizeStrategy implements SizeStrategy {
	}
}
