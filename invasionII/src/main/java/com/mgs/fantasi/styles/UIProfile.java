package com.mgs.fantasi.styles;

import com.mgs.fantasi.selectors.UISelector;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.tree.Tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UIProfile {
	private final Map<UISelector, UIStyle> styles = new HashMap<UISelector, UIStyle>();

	public void addStyle(UISelector uiSelector, UIStyle uiStyle) {
		styles.put(uiSelector, uiStyle);
	}

	public Set<UIStyle> findStylesFor(Tree<Wireframe, CollocationInfo> renderable) {
		Set<UIStyle> uiStyles = new HashSet<UIStyle>();
		for (UISelector matchingStrategy : styles.keySet()) {
			if (matchingStrategy.appliesTo(renderable)) {
				uiStyles.add(styles.get(matchingStrategy));
			}
		}
		return uiStyles;
	}
}
