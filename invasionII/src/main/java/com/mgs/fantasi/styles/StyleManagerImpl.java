package com.mgs.fantasi.styles;

import com.mgs.fantasi.properties.AllUndefinedUIPropertiesBuilder;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.properties.UIPropertiesBuilder;

import java.util.Set;

public class StyleManagerImpl implements StyleManager {

	@Override
	public UIProperties applyStyles(UIProperties uiProperties, Set<UIStyle> stylesToApply) {
		UIPropertiesBuilder withStylesApplied = new AllUndefinedUIPropertiesBuilder();
		withStylesApplied.withUIProperties(uiProperties);
		for (UIStyle style : stylesToApply) {
			withStylesApplied.withUIProperties(style.getUnderlyingBuilder().build());
		}
		return withStylesApplied.build();
	}
}
