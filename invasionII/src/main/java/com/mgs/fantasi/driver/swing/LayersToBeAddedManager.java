package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.LayeredWireframe;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.layer.LayerIterator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class LayersToBeAddedManager implements ToBeAddedManager {
	@Override
	public List<ToBeAddedBuilder<?, JPanel>> process(Wireframe<View> from) {
		final List<ToBeAddedBuilder<?, JPanel>> toBeAddedBuilderList2 = new ArrayList<ToBeAddedBuilder<?, JPanel>>();
		LayeredWireframe<View> layers = (LayeredWireframe<View>) from;
		layers.iterateInCrescendo(new LayerIterator<View>() {
			@Override
			public void on(int zIndex, View layer) {
				ToBeAddedBuilder<Integer, JPanel> toBeAddedBuilder = new ToBeAddedBuilder<Integer, JPanel>(zIndex, layer);
				toBeAddedBuilderList2.add(toBeAddedBuilder);
			}
		});
		return toBeAddedBuilderList2;

	}
}
