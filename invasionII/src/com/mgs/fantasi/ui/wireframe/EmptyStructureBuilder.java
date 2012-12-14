package com.mgs.fantasi.ui.wireframe;

public class EmptyStructureBuilder implements StructureBuilder
{
	@Override
	public ReadyForRendering produce() {
		return new ReadyForRendering(this);
	}
}
