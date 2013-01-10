package com.mgs.fantasi.wireframe;


import java.util.List;

@SuppressWarnings("unused")
public interface Wireframe<T> {
	WireframeType getType();

	boolean isEmpty();

	List<Placeholder<T>> getContent();
}
