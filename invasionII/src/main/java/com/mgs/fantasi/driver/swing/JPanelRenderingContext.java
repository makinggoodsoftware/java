package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingContext;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategy;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.driver.swing.layoutConstruction.LayoutConstructionManager;
import com.mgs.fantasi.driver.swing.layoutConstruction.LayoutConstructionStrategy;
import com.mgs.fantasi.driver.swing.layoutConstruction.ToBeAddedWithSpecifics;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.styles.StyleManager;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.Wireframe;

import javax.swing.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JPanelRenderingContext implements RenderingContext<JPanel> {
	private final UIProfile uiProfile;
	private final LayoutConstructionManager layoutConstructionManager;
	private final StyleManager styleManager;
	private final JPanelCreationStrategyFactory jPanelCreationStrategyFactory;

	public JPanelRenderingContext(UIProfile uiProfile, LayoutConstructionManager layoutConstructionManager, StyleManager styleManager, JPanelCreationStrategyFactory jPanelCreationStrategyFactory) {
		this.uiProfile = uiProfile;
		this.layoutConstructionManager = layoutConstructionManager;
		this.styleManager = styleManager;
		this.jPanelCreationStrategyFactory = jPanelCreationStrategyFactory;
	}

	public JPanelCreationStrategyFactory getJPanelCreationStrategyFactory() {
		return jPanelCreationStrategyFactory;
	}

	public LayoutConstructionManager getLayoutConstructionManager() {
		return layoutConstructionManager;
	}

	public StyleManager getStyleManager() {
		return styleManager;
	}

	public UIProfile getUiProfile() {
		return uiProfile;
	}

	@Override
	public JPanel render(View view) {
		return new JPanelRenderingProcessFactory(this).newRenderingProcess(view).render();
	}

	public static class JPanelRenderingProcessFactory {
		private final JPanelRenderingContext jPanelRenderingContext;


		public JPanelRenderingProcessFactory(JPanelRenderingContext jPanelRenderingContext) {
			this.jPanelRenderingContext = jPanelRenderingContext;
		}

		public JPanelRenderingProcess newRenderingProcess(View view) {
			UIProperties uiPropertiesWithStylesApplied = getStyleManager().applyStyles(view.getUiProperties(), getUIProfile().findStylesFor(view));
			Wireframe<View> content = view.buildContent();

			JPanelCreationStrategy baseCreationStrategy = getJPanelCreationStrategyFactory().forUIProperties(uiPropertiesWithStylesApplied);
			LayoutConstructionStrategy<?, ? extends Wireframe> layoutStrategy = getLayoutConstructionManager().createAndFillLayout(content);
			Map<Object, JPanelRenderingProcess> childrenProcesses = new HashMap<Object, JPanelRenderingProcess>();

			Iterator<? extends ToBeAddedWithSpecifics> iterator = layoutStrategy.getToBeAddedWithSpecifics().iterator();
			while (iterator.hasNext()) {
				ToBeAddedWithSpecifics toBeAddedWithSpecifics = iterator.next();
				JPanelRenderingProcess childProcessFactory = newRenderingProcess(toBeAddedWithSpecifics.getContent());
				childrenProcesses.put(toBeAddedWithSpecifics.getSpecifics(), childProcessFactory);
			}
			return new JPanelRenderingProcess(baseCreationStrategy, childrenProcesses, layoutStrategy);
		}

		private UIProfile getUIProfile() {
			return jPanelRenderingContext.getUiProfile();
		}

		private JPanelCreationStrategyFactory getJPanelCreationStrategyFactory() {
			return jPanelRenderingContext.getJPanelCreationStrategyFactory();
		}

		private StyleManager getStyleManager() {
			return jPanelRenderingContext.getStyleManager();
		}

		private LayoutConstructionManager getLayoutConstructionManager() {
			return jPanelRenderingContext.getLayoutConstructionManager();
		}
	}

	public static class JPanelRenderingProcess {
		private final JPanelCreationStrategy baseCreationStrategy;
		private final LayoutConstructionStrategy<?, ? extends Wireframe> layoutConstructionStrategy;
		private final Map<Object, JPanelRenderingProcess> childrenProcesses;

		public JPanelRenderingProcess(JPanelCreationStrategy baseCreationStrategy, Map<Object, JPanelRenderingProcess> childrenProcesses, LayoutConstructionStrategy<?, ? extends Wireframe> layoutConstructionStrategy) {
			this.baseCreationStrategy = baseCreationStrategy;
			this.childrenProcesses = childrenProcesses;
			this.layoutConstructionStrategy = layoutConstructionStrategy;
		}

		public JPanel render() {
			JPanel container = baseCreationStrategy.create();
			if (layoutConstructionStrategy.isEmpty()) return container;

			container.setLayout(layoutConstructionStrategy.getLayoutManager(container));

			for (Object specifics : childrenProcesses.keySet()) {
				JPanelRenderingProcess childRenderingProcessFactory = childrenProcesses.get(specifics);
				container.add(childRenderingProcessFactory.render(), specifics);
			}
			return container;
		}
	}
}
