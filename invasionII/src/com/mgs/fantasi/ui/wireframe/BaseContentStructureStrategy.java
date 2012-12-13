package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.views.View;

import java.awt.*;

public abstract class BaseContentStructureStrategy implements ContentStructureStrategy{

	@Override
	public Renderable createRenderable(View view, UIProfile uiProfile, Dimension dimension) {
		if (!view.renderConstraintsAreSatisfied()){
			throw new RuntimeException("Can't createRenderable " + view +  " since some of its constraints are not satisfied");
		}

		return new Renderable
				(
						view.getClass(),
						view.getShape(),
						view.getContentStructureStrategy().buildFrom(uiProfile, dimension),
						view.getMargin(),
						view.getName()
				);

	}
}
