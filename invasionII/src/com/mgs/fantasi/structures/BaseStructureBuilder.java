package com.mgs.fantasi.structures;

import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.ui.wireframe.Margin;
import com.mgs.fantasi.ui.wireframe.Structure;
import com.mgs.fantasi.ui.wireframe.Wireframe;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public abstract class BaseStructureBuilder<T extends BaseStructureBuilder> implements StructureBuilder{
	PolygonPointsIterator shape = new NativeRectanguarShape();
	private Margin margin = Margin.noMargin();
	private String name = "";

	@Override
	public final Wireframe build() {
		if (!constraintsAreSatisfied()){
			throw new RuntimeException("Can't build " +  this +  " since some of its constraints are not satisfied");
		}
		Structure content = buildLayoutAndChilds();
		if (content == null) throw new RuntimeException("Content can't be null, needs to be at lease GridFactory.empty()");
		return new Wireframe(getClass(), shape, content, margin, name);
	}

	protected abstract boolean constraintsAreSatisfied();

	public final BaseStructureBuilder withShape (PolygonPointsIterator shape){
		this.shape = shape;
		return this;
	}

	protected abstract Structure buildLayoutAndChilds();

	public T withMargin(Margin margin) {
		this.margin = margin;
		return (T) this;
	}

	public T withName(String name) {
		this.name = name;
		return (T) this;
	}

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

	@Override
	public final StructureBuilder newCopy() {
		T copy = copy ();
		copy.shape = this.shape;
		copy.margin = margin;
		copy.name = name;
		return copy;
	}

	protected abstract T copy();

	public Margin getMargin() {
		return margin;
	}

	public String getName() {
		return name;
	}

	public PolygonPointsIterator getShape() {
		return shape;
	}
}
