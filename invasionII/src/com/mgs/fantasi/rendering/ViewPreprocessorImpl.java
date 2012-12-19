package com.mgs.fantasi.rendering;

import com.mgs.fantasi.profile.UIProfile;
import com.mgs.fantasi.views.View;

public class ViewPreprocessorImpl implements ViewPreprocessor {

	@Override
	public Renderable prepareForRendering(View view, UIProfile uiProfile) {
		return new Renderable
		(
			view.buildChildViews().transform(toRenderables(uiProfile)).build(),
			view.takeUiPropertiesSnapshot().withStyles(uiProfile.findStylesFor(view))
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
