package com.mgs.fantasi.driver.swing.layoutConstruction;

import javax.swing.*;
import java.awt.*;

public interface LayoutProvider {
	LayoutManager getLayoutManager(JPanel container);
}
