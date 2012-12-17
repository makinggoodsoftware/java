package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.views.View;

public class MyRenderer {
	public Renderable render(View view) {
		Structure<Renderable> children = renderChildren(view);

		return new Renderable
		(
			view.getClass(),
			view.getShape(),
			children,
			view.getMargin(),
			view.getName()
		);
	}

	private Structure<Renderable> renderChildren(View view) {
		StructureBuilder<Renderable> builder = view.getChildStructure().transform(new StructureBuilderTransformer<View, Renderable>() {
			@Override
			public Renderable transform(View content) {
				return render(content);
			}
		});
		return builder.build();
	}


	public static interface StructureBuilderTransformer<T, Z> {
		public Z transform(T content);
	}
}
