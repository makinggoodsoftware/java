package com.mgs.fantasi.rendering;

import com.mgs.fantasi.profile.UIProfile;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.rendering.structure.Structure;
import com.mgs.fantasi.rendering.wireframe.Wireframe;
import com.mgs.fantasi.views.View;

public class ViewPreprocessorImpl implements ViewPreprocessor {

	@Override
	public Renderable prepareForRendering(View view, UIProfile uiProfile) {
        UIProperties uiPropertiesWithStylesApplied = view.takeUiPropertiesSnapshot().withStyles(uiProfile.findStylesFor(view));
        Wireframe<Renderable> wireframeOfRenderables = view.buildContent().transform(toRenderables(uiProfile));
        Structure<Renderable> structureOfRenderables = wireframeOfRenderables.build();

        return new Renderable
		(
            structureOfRenderables,
            uiPropertiesWithStylesApplied
		);
	}

	private WireframeTransformer<View, Renderable> toRenderables(final UIProfile uiProfile) {
		return new WireframeTransformer<View, Renderable>() {
			@Override
			public Renderable transform(View content) {
				return prepareForRendering(content, uiProfile);
			}
		};
	}


	public static interface WireframeTransformer<T, Z> {
		public Z transform(T content);
	}
}
