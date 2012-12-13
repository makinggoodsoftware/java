package com.mgs.fantasi.views;

import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.ui.wireframe.*;

import java.awt.*;

public class ViewRendererImpl implements ViewRenderer {
	@Override
	public Renderable createRenderable(View view, UIProfile uiProfile, Dimension dimension) {
		if (!view.renderConstraintsAreSatisfied()){
			throw new RuntimeException("Can't createRenderable " + view +  " since some of its constraints are not satisfied");
		}

		ContentStructureStrategy contentStructureStrategy = view.getContentStructureStrategy();
		Structure<Renderable> content = contentStructureStrategy.buildFrom(this, uiProfile, dimension);

		return new Renderable(view.getClass(), view.getShape(), content, view.getMargin(), view.getName());
	}

}
