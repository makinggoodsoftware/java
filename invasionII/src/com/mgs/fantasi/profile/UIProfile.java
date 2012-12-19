package com.mgs.fantasi.profile;

import com.mgs.fantasi.properties.selectors.UISelector;
import com.mgs.fantasi.rendering.Renderable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UIProfile {
	private final Map<UISelector, UIStyle> styles = new HashMap<UISelector, UIStyle>();

	public void addStyle(UISelector uiSelector, UIStyle uiStyle) {
		styles.put(uiSelector, uiStyle);
	}

	public Set<UIStyle> findStylesFor(Renderable renderable) {
		Set<UIStyle> uiStyles = new HashSet<UIStyle>();
		for (UISelector matchingStrategy : styles.keySet()) {
			if (matchingStrategy.appliesTo(renderable)){
				uiStyles.add(styles.get(matchingStrategy));
			}
		}
		return uiStyles;
	}
}
