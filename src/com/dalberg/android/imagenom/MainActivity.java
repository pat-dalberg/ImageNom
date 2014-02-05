/*
 * Copyright (C) 2014 Pat Dalberg 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dalberg.android.imagenom;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.dalberg.android.imagenom.bus.BusProvider;
import com.dalberg.android.imagenom.event.FlickrEvent;
import com.dalberg.android.imagenom.event.ImageSelectionEvent;
import com.dalberg.android.imagenom.utility.ImageDataUtility;
import com.squareup.otto.Subscribe;

public class MainActivity extends AbstractBaseActivity {
	
	private SearchFragment mSearchFragment;
	private ConfigFragment mConfigFragment;
	private ResultsFragment mResultsFragment;
	private PictureFragment mPictureFragment;
	private AboutFragment mAboutFragment;
	private FragmentManager mFragmentManager;
	private static final int SHARE_ID = 4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		BusProvider.getInstance().register(this);
		
	}
	
	@Override 
	protected void onPause(){
		super.onPause();
		BusProvider.getInstance().unregister(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu){
		try{
		if(mPictureFragment.isVisible()){
			menu.add(Menu.NONE, SHARE_ID, Menu.NONE, "share");
			return true;
		}else{
			return false;
		}
		}catch(NullPointerException e){
			return false;
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
			case R.id.about:
				
				FragmentTransaction transaction = mFragmentManager.beginTransaction();
				transaction.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left);
				transaction.remove(mConfigFragment);
				transaction.remove(mSearchFragment);
				transaction.replace(R.id.main_container, mAboutFragment, "ABOUT_FRAG");		
				transaction.addToBackStack("ABOUT_FRAG");
				transaction.commit();
				return true;
				
				default:
					return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void initUi() {
		
	}

	@Override
	public void initOps() {
		mFragmentManager = getFragmentManager();
		mSearchFragment = SearchFragment.newInstance();		
		mConfigFragment =  ConfigFragment.newInstance();		
		mAboutFragment = AboutFragment.newInstance();
		FragmentTransaction transaction = mFragmentManager.beginTransaction();
		transaction.add(R.id.main_container, mConfigFragment, "FRAG_CONFIG");
		transaction.add(R.id.main_container,mSearchFragment, "FRAG_SEARCH");
		transaction.addToBackStack("FRAG_CONFIG");
		transaction.addToBackStack("FRAG_SEARCH");
		transaction.commit();
	}

	@Override
	public void init() {
		initUi();
		initOps();		
	}
	
	@Subscribe
	public void onFlickrEvent(FlickrEvent event){
		mResultsFragment = ResultsFragment.newInstance();
		mResultsFragment.setImageData(ImageDataUtility.getImageDataArrayList(event.result));
		FragmentTransaction transaction = mFragmentManager.beginTransaction();
		transaction.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left);
		transaction.remove(mConfigFragment);
		transaction.remove(mSearchFragment);
		transaction.replace(R.id.main_container, mResultsFragment, "FRAG_RESULTS");		
		transaction.addToBackStack("FRAG_RESULTS");
		transaction.commit();		
	}
	
	@Subscribe
	public void onImageSelectionEvent(ImageSelectionEvent event){
		mPictureFragment = PictureFragment.newInstance();
		mPictureFragment.setPictureUrl(event.imageUrl);
		FragmentTransaction transaction = mFragmentManager.beginTransaction();
		transaction.remove(mResultsFragment);
		transaction.add(R.id.main_container, mPictureFragment, "FRAG_PICTURE");
		transaction.addToBackStack("FRAG_PICTURE");
		transaction.commit();
	}

}
