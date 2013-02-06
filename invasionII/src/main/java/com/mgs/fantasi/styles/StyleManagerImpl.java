package com.mgs.fantasi.styles;

import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.properties.UIPropertiesBuilder;

import java.util.Set;

import static com.mgs.fantasi.properties.UIPropertiesBuilderFactory.from;

public class StyleManagerImpl implements StyleManager {

	@Override
	public UIProperties applyStyles(UIProperties uiProperties, Set<UIProperties> uiPropertiesToApply) {
		UIPropertiesBuilder withStylesApplied = from(uiProperties);
		for (UIProperties uiPropertyToApply : uiPropertiesToApply) {
			withStylesApplied.withUIProperties(uiPropertyToApply);
		}
		return withStylesApplied.build();
	}

}
