package com.mgs.fantasi.wireframe;

public interface ConnectionManager<T, Z> {
	boolean accepts(Z linkInfo, Tree<T, Z> child);
}
