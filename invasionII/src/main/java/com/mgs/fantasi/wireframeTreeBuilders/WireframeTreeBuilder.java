package com.mgs.fantasi.wireframeTreeBuilders;

import com.mgs.fantasi.properties.EmptyRectangularUIPropertiesBuilder;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContentFactory;
import com.mgs.tree.Tree;

public interface WireframeTreeBuilder {
	Tree<Wireframe, CollocationInfo> build(WireframeContentFactory wireframeContentFactory);

	EmptyRectangularUIPropertiesBuilder getUiPropertiesBuilder();

	String getName();
}
