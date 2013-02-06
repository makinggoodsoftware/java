package com.mgs.fantasi.profile;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.selectors.UISelector;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.tree.Tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UIProfile {
	private final Map<UISelector, UIProperties> styles = new HashMap<UISelector, UIProperties>();

	public void addStyle(UISelector uiSelector, UIProperties uiProperties) {
		styles.put(uiSelector, uiProperties);
	}

	public Set<UIProperties> findStylesFor(Tree<Wireframe, CollocationInfo> renderable) {
		Set<UIProperties> uiStyles = new HashSet<UIProperties>();
		for (Map.Entry<UISelector, UIProperties> style : styles.entrySet()) {
			if (style.getKey().appliesTo(renderable)) {
				uiStyles.add(style.getValue());
			}
		}
		return uiStyles;
	}
}
