package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.driver.swing.SwingUINativeRenderer;
import com.mgs.fantasi.rendering.wireframe.*;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.views.View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LayoutConstructionStrategyFactory {
    public LayoutConstructionStrategy<?, ? extends Wireframe> processStructure(Wireframe content) {
		switch (content.getType()){
			case GRID:
				return grid().fillWith((GridWireframe) content);
			case LAYERS:
				return layers().fillWith((LayeredWireframe) content);
			case SIMPLE:
				return simple().fillWith((RectangleWireframe) content);
			case DELEGATE:
                return processStructure(((DelegateWireframe) content).getContent());
			case EMPTY:
				return new LayoutConstructionStrategyFactory().empty();
			default:
				throw new RuntimeException("Can't process the structure: " + content);
		}
	}

    public GridBaseLayoutConstructionStrategyImpl grid(){
		return new GridBaseLayoutConstructionStrategyImpl(new LayoutProvider(){
			@Override
			public LayoutManager getLayoutManager(JPanel container){
				return new GridBagLayout();
			}
		});
	}

	public LayerBaseLayoutConstructionStrategyImpl layers() {
		return new LayerBaseLayoutConstructionStrategyImpl(new LayoutProvider() {
			@Override
			public LayoutManager getLayoutManager(JPanel container) {
				return new OverlayLayout(container);
			}
		});
	}


	public SimpleBaseLayoutConstructionStrategyImpl simple() {
		return new SimpleBaseLayoutConstructionStrategyImpl(new LayoutProvider() {
			@Override
			public LayoutManager getLayoutManager(JPanel container) {
				return new GridBagLayout();
			}
		});
	}

	public LayoutConstructionStrategy<Void, ? extends Wireframe> empty() {
		return new LayoutConstructionStrategy<Void, Wireframe>() {

            @Override
			public OnGoingChildAddition<Void, Wireframe> queueForAddition(View child) {
				throw new RuntimeException("This ongoing construction is empty, nothing can be added to it");
			}

			@Override
			public void buildInto(JPanel container, SwingUINativeRenderer renderer, UIProfile uiProfile) {
				//do nothing
			}

            @Override
            public LayoutConstructionStrategy<Void, Wireframe> fillWith(Wireframe content) {
                throw new RuntimeException("Can't fill an empty structure!");
            }

            @Override
            public List<OnGoingChildAddition<Void, Wireframe>> getToBeAdded() {
                return new ArrayList<OnGoingChildAddition<Void, Wireframe>>();
            }
        };
	}
}
