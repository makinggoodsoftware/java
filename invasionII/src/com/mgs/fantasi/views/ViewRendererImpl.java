package com.mgs.fantasi.views;

import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.ui.wireframe.Structure;
import com.mgs.fantasi.ui.wireframe.Wireframe;

import java.awt.*;

public class ViewRendererImpl implements ViewRenderer {
	@Override
	public Wireframe render(View view, UIProfile uiProfile, Dimension dimension) {
		if (!view.constraintsAreSatisfied()){
			throw new RuntimeException("Can't render " + view +  " since some of its constraints are not satisfied");
		}
		Structure content = view.buildLayoutAndChilds();
		if (content == null) throw new RuntimeException("Content can't be null, needs to be at lease GridFactory.empty()");
		return new Wireframe(view.getClass(), view.getShape(), content, view.getMargin(), view.getName());
	}

}
