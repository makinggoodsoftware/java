package com.mgs.fantasi.properties;

import com.mgs.fantasi.properties.data.UIPropertyData;

import javax.swing.*;

public interface JPanelDecorator<T extends UIPropertyData> {
	JPanel decorate(JPanel nativeElement, T padding);
}
