package com.mgs.fantasi.profile;

public interface Mergeable<T extends Mergeable> {
	void merge(T into);
}
