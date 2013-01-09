package com.mgs.fantasi.driver.swing.jPanelCreation;

import com.mgs.fantasi.driver.swing.*;
import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeType;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ToBeAddedManagerFactory {
	public ToBeAddedManager forType(WireframeType type) {
		switch (type) {
			case GRID:
				return grid();
			case SIMPLE:
				return simple();
			case LAYERS:
				return layers();
			case EMPTY:
				return empty();
			default:
				throw new RuntimeException("Didn't expect to reach this point in the code");
		}
	}

	public ToBeAddedManager grid() {
		return new GridToBeAddedManager();
	}

	public ToBeAddedManager layers() {
		return new LayersToBeAddedManager();
	}

	public ToBeAddedManager simple() {
		return new SimpleToAddManager();
	}

	public ToBeAddedManager empty() {
		return new ToBeAddedManager() {
			@Override
			public List<ToBeAddedBuilder<?, JPanel>> process(Wireframe<View> grid) {
				return new ArrayList<ToBeAddedBuilder<?, JPanel>>();
			}
		};
	}
}
