package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.views.View;
import com.mgs.fantasi.views.ViewRenderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class LayeredContentStructureStrategy implements ContentStructureStrategy{
	@Override
	public final StructureFactory.StructureType getContentStructureType() {
		return StructureFactory.StructureType.LAYERS;
	}

	public abstract List<View> getLayers();

	public Structure<Renderable> buildFrom(ViewRenderer viewRenderer, UIProfile uiProfile, Dimension dimension) {
		List<View> layersAsViews = getLayers();
		List<Renderable> layers = new ArrayList<Renderable>();
		for (View view : layersAsViews) {
			layers.add(viewRenderer.createRenderable(view, uiProfile, dimension));
		}

		return new Layers<Renderable>(layers);
	}
}
