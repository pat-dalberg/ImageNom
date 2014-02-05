package com.dalberg.android.imagenom.event;

import com.dalberg.android.imagenom.model.FlickrResult;

public class FlickrEvent {
	public FlickrResult result;
	
	public FlickrEvent(FlickrResult flickrResult){
		this.result = flickrResult;
	}
}
