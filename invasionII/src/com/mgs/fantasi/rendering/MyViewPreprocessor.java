package com.mgs.fantasi.rendering;

import com.mgs.fantasi.rendering.wireframe.Wireframe;
import com.mgs.fantasi.views.View;

public class MyViewPreprocessor implements ViewPreprocessor {
	@Override
	public Renderable prepareForRendering(View view) {
		Wireframe<Renderable> children = view.buildChildViews().transform(toRenderables());

		return new Renderable
		(
			view.getClass(),
				children,
				view.getUiProperties().copy()
		);
	}

	private WireframeTransformer<View, Renderable> toRenderables() {
		return new WireframeTransformer<View, Renderable>() {
			@Override
			public Renderable transform(View content) {
				return prepareForRendering(content);
			}
		};
	}


	public static interface WireframeTransformer<T, Z> {
		public Z transform(T content);
	}
}
