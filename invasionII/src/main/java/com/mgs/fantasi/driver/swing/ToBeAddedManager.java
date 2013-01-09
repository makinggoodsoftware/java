package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.Wireframe;

import java.util.List;

public interface ToBeAddedManager {
	List<ToBeAddedBuilder> process(Wireframe<View> grid);
}
