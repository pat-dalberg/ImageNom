package com.dalberg.android.imagenom.utility;

import com.dalberg.android.imagenom.model.FlickrPhoto;

public class FlickrPictureUrlUtility {
	
	// "http://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_[mstzb].jpg";
	
	private static String FLICKR_BASE_URL = "http://farm%d.staticflickr.com/%s/%s_%s_%s.jpg";
	
	public static String getFlickrThumbnail(FlickrPhoto flickrPhoto){
		String url = String.format(FLICKR_BASE_URL, flickrPhoto.farm, flickrPhoto.server, flickrPhoto.id, flickrPhoto.secret, "t");
		return url;
	}
	
	public static String getFlickrLarge(FlickrPhoto flickrPhoto){
		String url = String.format(FLICKR_BASE_URL, flickrPhoto.farm, flickrPhoto.server, flickrPhoto.id, flickrPhoto.secret, "b");
		return url;
	}

}
