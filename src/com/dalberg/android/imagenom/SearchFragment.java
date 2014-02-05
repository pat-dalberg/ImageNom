package com.dalberg.android.imagenom;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.dalberg.android.imagenom.async.FlickrClient;
import com.dalberg.android.imagenom.async.ImgurClient;

public class SearchFragment extends AbstractBaseFragment {
	
	private EditText etSearch;
	private TextView tvSearching;
	private ProgressBar pbSearching;
	private View mView;
	
	private ImgurClient mImgurClient;
	private FlickrClient mFlickrClient;
	
	private static final long ANIM_DURATION = 750;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		mView = inflater.inflate(R.layout.search_fragment, container, false);
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
		etSearch = (EditText)mView.findViewById(R.id.etSearch);
		etSearch.setOnEditorActionListener(searchListener);
		tvSearching = (TextView)mView.findViewById(R.id.tvSearching);
		tvSearching.setTranslationY(500f);
		pbSearching = (ProgressBar)mView.findViewById(R.id.pbSearching);
		pbSearching.setAlpha(0f);
		
	}

	@Override
	public void initOps() {
//		mImgurClient = new ImgurClient();		
		mFlickrClient = new FlickrClient();
	}
	
	public static SearchFragment newInstance(){
		SearchFragment searchFragment = new SearchFragment();
		return searchFragment;
	}
	
	private OnEditorActionListener searchListener = new OnEditorActionListener() {
		
		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			if(actionId == EditorInfo.IME_ACTION_SEARCH){
				String search = v.getText().toString();
				if(search.length() == 0){
					// TODO toast 'empty search.' enter search term
				}else{
					mFlickrClient.doFlickrSearch(search);
					ObjectAnimator fadeOutAnim = ObjectAnimator.ofFloat(etSearch, "alpha", 1f, 0f);
					ObjectAnimator fadeInAnim = ObjectAnimator.ofFloat(pbSearching, "alpha", 0f, 1f);
					ObjectAnimator moveUp = ObjectAnimator.ofFloat(tvSearching, "translationY", 500f, 0f);
					AnimatorSet searchWaitAnims = new AnimatorSet();
					searchWaitAnims.playTogether(fadeOutAnim, fadeInAnim, moveUp);
					searchWaitAnims.setDuration(ANIM_DURATION);
					searchWaitAnims.start();

				}
			}
			return false;
		}
	};
	
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
