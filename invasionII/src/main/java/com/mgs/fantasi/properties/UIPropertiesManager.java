package com.mgs.fantasi.properties;

import com.mgs.fantasi.properties.data.UIPropertyData;

import java.util.Map;
import java.util.Set;

import static com.mgs.fantasi.properties.UIPropertiesBuilderFactory.copy;

public class UIPropertiesManager {
	public UIPropertiesProvider applyStyles(UIPropertiesProvider uiProperties, Set<UIProperties> styles) {
		UIPropertiesBuilder aux = copy(uiProperties);
		for (UIProperties style : styles) {
			for (Map.Entry<UIPropertyId, UIProperty<? extends UIPropertyData>> uiPropertyEntry : style) {
				aux.withUIProperty(uiPropertyEntry.getKey(), uiPropertyEntry.getValue());
			}
		}
		return aux.build();
	}
}
