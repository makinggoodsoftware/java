package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.views.View;

public class DelegateStructureBuilder implements StructureBuilder{
	private View content;

	@Override
	public ReadyForRendering produce() {
		return new ReadyForRendering(this);
	}

	public DelegateStructureBuilder withContent(View content) {
		this.content = content;
		return this;
	}
}
