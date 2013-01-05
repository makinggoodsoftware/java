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
import java.util.Iterator;
import java.util.List;

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
		return new JPanelRenderingProcess(this).prepare(view).render();
	}

	public static class JPanelRenderingProcess {
		private final JPanelRenderingContext jPanelRenderingContext;
		private JPanelCreationStrategy outsideCreationStrategy;
		private List<? extends ToBeAddedWithSpecifics> toBeAddedWithSpecifics;
		private LayoutConstructionStrategy<?, ? extends Wireframe> layoutConstructionStrategy;

		public JPanelRenderingProcess(JPanelRenderingContext jPanelRenderingContext) {
			this.jPanelRenderingContext = jPanelRenderingContext;
		}

		public JPanelRenderingProcess prepare(View view) {
			UIProperties uiPropertiesWithStylesApplied = getStyleManager().applyStyles(view.getUiProperties(), getUIProfile().findStylesFor(view));
			Wireframe<View> content = view.buildContent();
			outsideCreationStrategy = getJPanelCreationStrategyFactory().forUIProperties(uiPropertiesWithStylesApplied);
			layoutConstructionStrategy = getLayoutConstructionManager().createAndFillLayout(content);
			toBeAddedWithSpecifics = layoutConstructionStrategy.getToBeAddedWithSpecifics();

			return this;
		}

		public JPanel render() {
			JPanel container = outsideCreationStrategy.create();
			if (layoutConstructionStrategy.isEmpty()) return container;

			container.setLayout(layoutConstructionStrategy.getLayoutManager(container));
			Iterator<? extends ToBeAddedWithSpecifics> iterator = toBeAddedWithSpecifics.iterator();
			while (iterator.hasNext()) {
				ToBeAddedWithSpecifics toBeAddedWithSpecifics = iterator.next();
				JPanel child = new JPanelRenderingProcess(jPanelRenderingContext).prepare(toBeAddedWithSpecifics.getContent()).render();
				container.add(child, toBeAddedWithSpecifics.getSpecifics());
			}
			return container;
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
}
