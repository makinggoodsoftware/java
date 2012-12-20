package com.mgs.fantasi.driver.swing.layoutConstruction;

import javax.swing.*;

public interface OnGoingLayoutConstruction<T> {
	OnGoingChildContentConstruction<T> add(JPanel childAsNativeComponent);

	void buildInto(JPanel container);
}
