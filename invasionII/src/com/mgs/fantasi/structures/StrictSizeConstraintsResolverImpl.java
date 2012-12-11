package com.mgs.fantasi.structures;

import com.mgs.fantasi.ui.wireframe.Wireframe;

import java.awt.*;

public class StrictSizeConstraintsResolverImpl implements StrictSizeConstraintsResolver {
	@Override
	public Wireframe resolve(Wireframe wireframe, Dimension dimension) {
		return wireframe;
	}
}
