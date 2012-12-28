package com.mgs.fantasi.rendering.wireframe;

import com.mgs.fantasi.Structurable;
import com.mgs.fantasi.rendering.ViewPreprocessorImpl;
import com.mgs.fantasi.rendering.structure.StructureType;
import com.mgs.fantasi.rendering.structure.layer.LayerIterator;

import java.util.ArrayList;
import java.util.List;

public class LayeredWireframe<T extends Structurable> implements Wireframe<T> {
    private List<T> layers;

    @Override
    public <Z extends Structurable> Wireframe<Z> transform(ViewPreprocessorImpl.WireframeTransformer<T, Z> transformer) {
        List<Z> transformedLayers = new ArrayList<Z>();
        for (T layer : layers) {
            transformedLayers.add(transformer.transform(layer));
        }
        return new LayeredWireframe<Z>().withLayers(transformedLayers);
    }

    @Override
    public StructureType getType() {
        return StructureType.LAYERS;
    }

    public Wireframe<T> withLayers(List<T> layers) {
        this.layers = layers;
        return this;
    }

    public void iterateInCrescendo(LayerIterator<T> layerIterator) {
        int zIndex = 0;
        for (int i = layers.size() - 1; i >= 0; i--) {
            layerIterator.on(zIndex, layers.get(i));
            zIndex++;
        }
    }
}
