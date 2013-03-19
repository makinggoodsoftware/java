package com.mgs.fantasi.structure.bluePrintPatterns;

import com.mgs.fantasi.structure.bluePrint.BluePrint;
import com.mgs.fantasi.wireframe.Wireframe;

public interface BluePrintBuilderPattern {

	void initialise(String name, Wireframe wireframe);

	BluePrint buildBlueprint();

}
