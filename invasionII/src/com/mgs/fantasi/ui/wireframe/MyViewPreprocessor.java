package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.views.View;

public class MyViewPreprocessor implements ViewPreprocessor {
	@Override
	public Renderable prepareForRendering(View view) {
		Wireframe<Renderable> children = view.toWireframe().transformContent(toRenderables());

		return new Renderable
		(
			view.getClass(),
			view.getUiProperties().getShape(),
			children,
			view.getUiProperties().getMargin(),
			view.getUiProperties().getName()
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
