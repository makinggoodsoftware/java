package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.Wireframe;

import javax.swing.*;
import java.util.List;

public interface ToBeAddedManager {
	List<ToBeAddedBuilder<?, JPanel>> process(Wireframe<View> grid);
}
