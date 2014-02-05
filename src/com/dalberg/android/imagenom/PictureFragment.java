package com.dalberg.android.imagenom;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PictureFragment extends AbstractBaseFragment {
	
	private View mView;
	private ImageView ivPicture;
	private String mPictureUrl;
	private Context mContext;
	
	public static PictureFragment newInstance(){
		return new PictureFragment();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		mView = inflater.inflate(R.layout.picture_fragment, container, false);
		return mView;
	}
	
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
		ivPicture = (ImageView)mView.findViewById(R.id.ivPicture);

	}

	@Override
	public void initOps() {
		Picasso.with(getActivity().getApplicationContext())
		.load(mPictureUrl)
		.placeholder(R.drawable.placekitten400_400)
		.into(ivPicture);

	}
	
	public void setPictureUrl(String url){
		mPictureUrl = url;
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
