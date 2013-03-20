package com.mgs.fantasi.structure.bluePrintPatterns;

import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.wireframe.Wireframe;

public interface StructureContentBuilder {

	Structure buildStructure(String name, Wireframe wireframe);

}
