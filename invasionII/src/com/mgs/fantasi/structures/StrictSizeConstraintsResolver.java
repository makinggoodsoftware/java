package com.mgs.fantasi.structures;

import com.mgs.fantasi.ui.wireframe.Wireframe;

import java.awt.*;

public interface StrictSizeConstraintsResolver {
	Wireframe resolve(Wireframe wireframe, Dimension dimension);
}
