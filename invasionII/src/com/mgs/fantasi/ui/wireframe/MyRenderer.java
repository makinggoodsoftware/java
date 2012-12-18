package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.views.View;

public class MyRenderer implements Renderer {
	@Override
	public Renderable render(View view) {
		Wireframe<Renderable> children = view.toWireframe().transformContent(toRenderables());

		return new Renderable
		(
			view.getClass(),
			view.getShape(),
			children,
			view.getMargin(),
			view.getName()
		);
	}

	private WireframeTransformer<View, Renderable> toRenderables() {
		return new WireframeTransformer<View, Renderable>() {
			@Override
			public Renderable transform(View content) {
				return render(content);
			}
		};
	}


	public static interface WireframeTransformer<T, Z> {
		public Z transform(T content);
	}
}
