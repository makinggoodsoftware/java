package com.mgs.fantasi.ui.driver;

import com.mgs.fantasi.ui.driver.swing.SwingUIDisplayManager;
import com.mgs.fantasi.ui.driver.swing.SwingUINativeElementCreatorStrategy;
import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.ui.profile.UIStyle;
import com.mgs.fantasi.ui.wireframe.*;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class UIDriver<T> {
	private final UIProfile uiProfile;
	private final UINativeElementCreatorStrategy<T> uiNativeElementCreatorStrategy;
	private final UIDIsplayManager<T> uiDIsplayManager;


	public static UIDriver<JPanel> forSwing (UIProfile uiProfile){
		return new UIDriver<JPanel>(uiProfile, new SwingUINativeElementCreatorStrategy(), new SwingUIDisplayManager());
	}

	private UIDriver(UIProfile uiProfile, UINativeElementCreatorStrategy<T> uiStrategy, UIDIsplayManager<T> uiDIsplayManager) {
		this.uiProfile = uiProfile;
		this.uiNativeElementCreatorStrategy = uiStrategy;
		this.uiDIsplayManager = uiDIsplayManager;
	}

	public void show(final Wireframe wireframe, Dimension dimension) {
		T uiNativeComponent = buildNativeElement(wireframe);
		uiDIsplayManager.showPacked(uiNativeComponent, dimension);
	}

	private T buildNativeElement(final Wireframe wireframe) {
		Set<UIStyle> uiStyles = uiProfile.findStylesFor(wireframe);
		final T parent = uiNativeElementCreatorStrategy.createSkeleton(wireframe, uiStyles);
		final Structure content = wireframe.getContent();
		if (content instanceof Grid){
			processGridChilds(parent, (Grid<Wireframe>) content);
		}else if (content instanceof Layers){
			processLayerChilds(parent, (Layers<Wireframe>) content);
		}else{
			throw new RuntimeException("Can't process the wireframe: " + wireframe);
		}
		return parent;
	}

	private void processLayerChilds(final T parent, final Layers<Wireframe> layers) {
		layers.iterateInCrescendo (new LayerIterator<Wireframe>(){
			@Override
			public void on(int zIndex, Wireframe layer, Margin margin) {
			}
		});
	}

	private void processGridChilds(final T parent, Grid<Wireframe> grid) {
		grid.itereateCellsWith(new CellIterator<Wireframe>() {
			@Override
			public void on(int x, int y, CellContent<Wireframe> cell) {
				if (cell == null) {
					throw new RuntimeException
							("Error building the UI native element when inspecting the content of the original" +
									" wireframe. This should not happen ever! There must have been an error on the" +
									" build call previous to the transformation into a native UI element must be badly constructed");
				}
				Wireframe child = cell.getContent();
				T childAsNativeComponent = buildNativeElement(child);
				uiNativeElementCreatorStrategy.compose(parent, childAsNativeComponent, child.getSizeStrategy(), x, y, cell.getWidthSizeRatio(), cell.getHeightSizeRatio());
			}
		});
	}

}
