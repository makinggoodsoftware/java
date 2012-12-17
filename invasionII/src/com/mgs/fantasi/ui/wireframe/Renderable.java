package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.views.View;

public class Renderable implements Structurable{
	private final Class<? extends View> view;
	private final PolygonPointsIterator shape;
	private final Structure content;
	private final Margin margin;
	private String name;

	public Renderable(Class<? extends View> view, PolygonPointsIterator shape, Structure<Renderable> content, Margin margin, String name) {
		if (shape==null) throw new IllegalArgumentException("The shape of something renderable can't be null");
		this.view = view;
		this.shape = shape;
		this.content = content;
		this.margin = margin;
		this.name = name;
	}

	public Class<? extends View> getView() {
		return view;
	}

	public PolygonPointsIterator getShape() {
		return shape;
	}

	public Structure<Renderable> getContent() {
		return content;
	}

	public Margin getMargin() {
		return margin;
	}

	@Override
	public String toString() {
		return "Renderable{" +
				"content=" + content +
				", view=" + view +
				", shape=" + shape +
				", margin=" + margin +
				", name='" + name + '\'' +
				'}';
	}

	public String getName() {
		return name;
	}
}
