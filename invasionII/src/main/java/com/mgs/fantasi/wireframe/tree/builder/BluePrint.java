package com.mgs.fantasi.wireframe.tree.builder;

import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.tree.Structure;

public interface BluePrint {
	Structure build();

	Wireframe getRootWireframe();

	String getName();
}
