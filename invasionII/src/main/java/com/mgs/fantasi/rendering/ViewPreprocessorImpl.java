package com.mgs.fantasi.rendering;

import com.mgs.fantasi.profile.UIProfile;
import com.mgs.fantasi.views.View;

public class ViewPreprocessorImpl implements ViewPreprocessor {

	@Override
	public Renderable prepareForRendering(View view, UIProfile uiProfile) {

        return new Renderable
		(
        );
	}


    public static interface WireframeTransformer<T, Z> {
		public Z transform(T content);
	}
}
