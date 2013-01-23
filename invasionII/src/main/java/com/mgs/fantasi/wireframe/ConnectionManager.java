package com.mgs.fantasi.wireframe;

public interface ConnectionManager<T> {
	boolean accepts(WireframeChildElement<T> toBeAdded);
}
