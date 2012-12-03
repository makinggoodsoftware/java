package com.mgs.fantasi.structures;

import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.ui.profile.SizeStrategy;
import com.mgs.fantasi.ui.wireframe.Structure;
import com.mgs.fantasi.ui.wireframe.Wireframe;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseStructureBuilder implements StructureBuilder{
	PolygonPointsIterator shape = new NativeRectanguarShape();
	private SizeStrategy sizeStrategy = new NativeDefaultSizeStrategy();
	private List<BuildingConstraint> constraints = new ArrayList<BuildingConstraint>();

	@Override
	public final Wireframe build() {
		if (!constraintsAreSatisfied()){
			throw new RuntimeException("Can't build " +  this +  " since some of its constraints are not satisfied");
		}
		Structure content = buildLayoutAndChilds();
		if (content == null) throw new RuntimeException("Content can't be null, needs to be at lease GridFactory.empty()");
		return new Wireframe(getClass(), shape, content, sizeStrategy);
	}

	protected abstract boolean constraintsAreSatisfied();

	public final BaseStructureBuilder withShape (PolygonPointsIterator shape){
		this.shape = shape;
		return this;
	}

	public StructureBuilder withSizeStrategy(SizeStrategy sizeStrategy) {
		this.sizeStrategy = sizeStrategy;
		return this;
	}

	protected abstract Structure buildLayoutAndChilds();

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
