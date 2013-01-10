package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.RenderingProcess;
import com.mgs.fantasi.driver.RenderingProcessFactory;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategy;
import com.mgs.fantasi.driver.swing.jPanelCreation.JPanelCreationStrategyFactory;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.styles.StyleManager;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.Placeholder;
import com.mgs.fantasi.wireframe.PlaceholderFactory;
import com.mgs.fantasi.wireframe.Wireframe;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;

public class JPanelRenderingProcessFactory implements RenderingProcessFactory<JPanel> {
	private final JPanelCreationStrategyFactory jPanelCreationStrategyFactory;
	private final StyleManager styleManager;
	private final PlaceholderFactory placeholderFactory;


	public JPanelRenderingProcessFactory(StyleManager styleManager, JPanelCreationStrategyFactory jPanelCreationStrategyFactory, PlaceholderFactory placeholderFactory) {
		this.jPanelCreationStrategyFactory = jPanelCreationStrategyFactory;
		this.styleManager = styleManager;
		this.placeholderFactory = placeholderFactory;
	}

	@Override
	public RenderingProcess<JPanel> newRenderingProcess(View view, final UIProfile uiProfile) {
		System.out.println("Rendering process for view: " + view + ": START");
		Wireframe<View> from = view.buildContent(placeholderFactory);
		UIProperties uiPropertiesWithStylesApplied = styleManager.applyStyles(view.getUiProperties(), uiProfile.findStylesFor(view));
		JPanelCreationStrategy baseCreationStrategy = jPanelCreationStrategyFactory.forUIProperties(uiPropertiesWithStylesApplied, from.getType());
		System.out.println("Rendering process for view: " + view + ": Base creation strategy = " + baseCreationStrategy);

		System.out.println("Rendering process for view: " + view + ": About to create its content");
		List<ToBeAdded<JPanel>> content = createContent(uiProfile, from);
		System.out.println("Rendering process for view: " + view + ": Content created");
		JPanelRenderingProcess jPanelRenderingProcess = new JPanelRenderingProcess(baseCreationStrategy, content);
		System.out.println("Rendering process for view: " + view + ": END");
		return jPanelRenderingProcess;
	}

	private List<ToBeAdded<JPanel>> createContent(UIProfile uiProfile, Wireframe<View> from) {
		System.out.println("Creating content for " + from + " - START");
		List<ToBeAdded<JPanel>> childObjects = new ArrayList<ToBeAdded<JPanel>>();
		for (Placeholder<View> viewPlaceholder : from.getContent()) {
			System.out.println("Creating content for " + from + " - Placeholder = " + reflectionToString(viewPlaceholder));
			RenderingProcess<JPanel> jPanelRenderingProcess = newRenderingProcess(viewPlaceholder.getContent(), uiProfile);
			childObjects.add(new ToBeAdded<JPanel>(jPanelRenderingProcess, viewPlaceholder));

		}
		System.out.println("Creating content for " + from + " - END");
		return childObjects;
	}

}
