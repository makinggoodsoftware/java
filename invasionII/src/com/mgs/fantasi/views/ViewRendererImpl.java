package com.mgs.fantasi.views;

import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.ui.wireframe.*;

import java.awt.*;
import java.util.*;
import java.util.List;

public class ViewRendererImpl implements ViewRenderer {
	@Override
	public Renderable createRenderable(View view, UIProfile uiProfile, Dimension dimension) {
		if (!view.renderConstraintsAreSatisfied()){
			throw new RuntimeException("Can't createRenderable " + view +  " since some of its constraints are not satisfied");
		}

		Structure<Renderable> contentStructure = createContentStructure(view, uiProfile, dimension);

		return new Renderable(view.getClass(), view.getShape(), contentStructure, view.getMargin(), view.getName());
	}

	public Structure<Renderable> createContentStructure(View view, UIProfile uiProfile, Dimension dimension) {
		ContentStructureStrategy contentStructureStrategy = view.getContentStructureStrategy();

		switch (contentStructureStrategy.getContentStructureType()){
			case EMPTY: return empty();
			case GRID: return gridFrom((GridContentStructureStrategy) contentStructureStrategy, uiProfile, dimension);
			case LAYERS: return layersFrom((LayeredContentStructureStrategy) contentStructureStrategy, uiProfile, dimension);
			case SIMPLE: return simpleFrom((SimpleContentStructureStrategy) contentStructureStrategy, uiProfile, dimension);
		}
		throw new RuntimeException("");
	}

	private Structure<Renderable> gridFrom(final GridContentStructureStrategy gridContentStructureStrategy, final UIProfile uiProfile, final Dimension dimension) {
		ArrayListGrid<Renderable> structure = new ArrayListGrid<Renderable>(gridContentStructureStrategy.getDimension());
		structure.fillCells(new CellContentGenerator<Renderable>() {
			@Override
			public CellContent<Renderable> generateContentFor(int x, int y) {
				CellContent<View> beforeTransformation = gridContentStructureStrategy.getCellContentFor(x, y);
				CellContent<Renderable> cell = new CellContent<Renderable>(
					createRenderable(beforeTransformation.getContent(), uiProfile, dimension),
					beforeTransformation.getHeightSizeRatio(),
					beforeTransformation.getWidthSizeRatio()
				);
				return cell;
			}
		});
		return structure;
	}

	private Structure<Renderable> simpleFrom(SimpleContentStructureStrategy simpleContentStructureStrategy, UIProfile uiProfile, Dimension dimension) {
		Renderable content = createRenderable(simpleContentStructureStrategy.getContent(), uiProfile, dimension);
		return new SimpleStructure<Renderable>(content);
	}

	private Structure<Renderable> layersFrom(LayeredContentStructureStrategy layeredContentStructureStrategy, UIProfile uiProfile, Dimension dimension) {
		List<View> lasyersAsViews = layeredContentStructureStrategy.getLayers();
		List<Renderable> layers = new ArrayList<Renderable>();
		for (View view : lasyersAsViews) {
			layers.add(createRenderable(view, uiProfile, dimension));
		}

		return new Layers<Renderable>(layers);
	}

	private Structure<Renderable> empty() {
		return new EmptyStructure ();
	}
}
