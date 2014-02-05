package com.dalberg.android.imagenom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AboutFragment extends AbstractBaseFragment {
	
	private View mView;
	
	public static AboutFragment newInstance(){
		return new AboutFragment();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		mView = inflater.inflate(R.layout.about_fragment, container, false);
		return mView;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initUi() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initOps() {
		// TODO Auto-generated method stub

	}
	
	public float getXFraction() {
		if(mView.getX() == 0 || mView.getWidth() == 0){
			return 0;
		}
        return mView.getX() / mView.getWidth();
    }

    public void setXFraction(float xFraction) {
        final int width = mView.getWidth();
        mView.setX((width > 0) ? (xFraction * width) : -9999);
    }

}
