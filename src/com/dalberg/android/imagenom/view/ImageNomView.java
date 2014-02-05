package com.dalberg.android.imagenom.view;

import android.content.Context;
import android.widget.RelativeLayout;

public class ImageNomView extends RelativeLayout {

	public ImageNomView(Context context) {
		super(context);
		
	}
	
	public float getXFraction() {
		if(getX() == 0 || getWidth() == 0){
			return 0;
		}
        return getX() / getWidth();
    }

    public void setXFraction(float xFraction) {
        final int width = getWidth();
        setX((width > 0) ? (xFraction * width) : -9999);
    }

}
