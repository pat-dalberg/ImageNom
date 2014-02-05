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

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.dalberg.android.imagenom.adapter.SearchResultsAdapter;
import com.dalberg.android.imagenom.bus.BusProvider;
import com.dalberg.android.imagenom.event.FlickrEvent;
import com.dalberg.android.imagenom.event.ImageSelectionEvent;
import com.dalberg.android.imagenom.model.FlickrResult;
import com.dalberg.android.imagenom.model.ImageData;
import com.dalberg.android.imagenom.view.ImageNomView;
import com.squareup.otto.Produce;

public class ResultsFragment extends AbstractBaseFragment {
	
	private GridView gvResults;
	private View mView;
	private ArrayList<ImageData> mImages;
	
	public static ResultsFragment newInstance(){
		return new ResultsFragment();
	}
	
	public void setImageData(ArrayList<ImageData> images){
		mImages = images;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		mView = inflater.inflate(R.layout.results_grid_fragment, container, false);
		return mView;
	}

	@Override
	public void onResume(){
		super.onResume();
		init();
	}
	
	@Override
	public void onPause(){
		super.onPause();
	}
	
	@Override
	public void init() {
		initUi();
		initOps();
	}

	@Override
	public void initUi() {
		gvResults = (GridView)mView.findViewById(R.id.gvResults);
	}

	@Override
	public void initOps() {
		SearchResultsAdapter adapter = new SearchResultsAdapter(getActivity(), 0, mImages);
		gvResults.setAdapter(adapter);
		gvResults.setOnItemClickListener(picSelected);

	}
	
	private OnItemClickListener picSelected = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			ImageData data = mImages.get(position);
			BusProvider.getInstance().post(produceImageSelectionEvent(data));
			
		}
	};
	
	
	@Produce
	public ImageSelectionEvent produceImageSelectionEvent(ImageData imageData){
		return new ImageSelectionEvent(imageData);
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
