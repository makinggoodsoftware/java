package com.mgs.fantasi.driver.swing.layoutProvider;

import javax.swing.*;
import java.awt.*;

public interface LayoutProvider {
	LayoutManager getLayoutManager(JPanel container);

	boolean isEmpty();
}
