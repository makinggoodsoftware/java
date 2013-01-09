package com.mgs.fantasi.driver;


import com.mgs.fantasi.driver.swing.ToBeAdded;

import javax.swing.*;
import java.util.List;

public interface RenderingProcess<T> {
	T render();

	class RenderingContent {
		private final List<ToBeAdded<?, JPanel>> childrenProcesses;

		public RenderingContent(List<ToBeAdded<?, JPanel>> childrenProcesses) {
			this.childrenProcesses = childrenProcesses;
		}

		public List<ToBeAdded<?, JPanel>> getChildrenProcesses() {
			return childrenProcesses;
		}

		public boolean isEmpty() {
			return childrenProcesses.size() == 0;
		}

	}
}
