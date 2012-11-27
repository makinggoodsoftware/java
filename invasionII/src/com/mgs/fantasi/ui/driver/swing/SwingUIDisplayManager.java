package com.mgs.fantasi.ui.driver.swing;

import com.mgs.fantasi.ui.driver.UIDIsplayManager;

import javax.swing.*;
import java.awt.*;

public class SwingUIDisplayManager implements UIDIsplayManager<JPanel> {

	public void packed(final JPanel defaultBoard) {
		JFrame jFrame = new JFrame();
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = jFrame.getContentPane();
		contentPane.add(defaultBoard);

		jFrame.pack();
	}

	@Override
	public void showPacked(final JPanel uiNativeComponent, final Dimension dimension) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				uiNativeComponent.setPreferredSize(dimension);
				packed(uiNativeComponent);
			}
		});
	}
}
