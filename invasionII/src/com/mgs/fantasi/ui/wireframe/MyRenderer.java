package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.views.View;

public class MyRenderer {
	public Renderable render(View view) {
		System.out.println("Rendering view: " + view);
		StructureBuilder<Renderable> renderableStructureBuilder = prepareContentWith(view);

		return new Renderable
		(
			view.getClass(),
			view.getShape(),
			renderableStructureBuilder.build(),
			view.getMargin(),
			view.getName()
		);
	}

	private StructureBuilder<Renderable> prepareContentWith(View view) {
		System.out.println("Preparing content for " + view);
		return view.createRenderingStructure().transform(new StructureBuilderTransformer<View, Renderable>(){
			@Override
			public Renderable transform(View content) {
				System.out.println("Transforming " + content);
				return render(content);
			}
		});
	}


	public static interface StructureBuilderTransformer<T, Z> {
		public Z transform(T content);
	}
}
