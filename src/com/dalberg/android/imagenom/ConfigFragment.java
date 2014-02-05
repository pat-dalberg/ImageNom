package com.dalberg.android.imagenom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

public class ConfigFragment extends AbstractBaseFragment {
	
	private View mView;
	private CheckBox cbGoogle, cbFlickr, cbImgur;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		mView = inflater.inflate(R.layout.config_fragment, container, false);
		return mView;
	}
	
//	@Override
//	public void onActivityCreated(Bundle savedInstanceState){
//		init();
//	}
	
	@Override
	public void onResume(){
		super.onResume();
		init();
	}

	@Override
	public void init() {
		initUi();
		initOps();

	}

	@Override
	public void initUi() {
		cbGoogle = (CheckBox)mView.findViewById(R.id.cbGoogle);  
		cbFlickr = (CheckBox)mView.findViewById(R.id.cbFlickr);  
		cbImgur = (CheckBox)mView.findViewById(R.id.cbImgur);

	}

	@Override
	public void initOps() {
		// TODO Auto-generated method stub

	}
	
	public static ConfigFragment newInstance(){
		ConfigFragment configFragment = new ConfigFragment();
		return configFragment;
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
