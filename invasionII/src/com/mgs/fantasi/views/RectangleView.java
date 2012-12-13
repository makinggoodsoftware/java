package com.mgs.fantasi.views;

import com.mgs.fantasi.ui.wireframe.*;

public class RectangleView extends BaseView<RectangleView> {
	private View content;

	private RectangleView(View content) {
		this.content = content;
	}

	private RectangleView() {
	}

	public static RectangleView emptyRectangle() {
		return new RectangleView();
	}

	public RectangleView withContent(View content) {
		this.content = content;
		return this;
	}

	@Override
	public boolean renderConstraintsAreSatisfied() {
		return true;
	}

	@Override
	public RectangleView copy() {
		return new RectangleView(content.newCopy());
	}

	@Override
	public Structure<View> getContent() {
		if (content == null) return GridFactory.empty(View.class);
		return new SimpleStructure<View>(content);
	}

	@Override
	public StructureFactory.StructureType getContentStructureType() {
		return null;
	}

	@Override
	public ContentStructureStrategy getContentStructureStrategy() {
		return content == null ?
			new EmptyContentStructureStrategy() :
			new SimpleContentStructureStrategy() {
				@Override
				public View getContent() {
					return content;
				}
			};
	}

}