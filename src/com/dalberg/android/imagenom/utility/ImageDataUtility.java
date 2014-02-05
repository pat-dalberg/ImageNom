package com.dalberg.android.imagenom.utility;

import java.util.ArrayList;
import java.util.HashMap;

import com.dalberg.android.imagenom.model.FlickrPhoto;
import com.dalberg.android.imagenom.model.FlickrResult;
import com.dalberg.android.imagenom.model.ImageData;

public class ImageDataUtility {
	
	public static ArrayList<ImageData> getImageDataArrayList(FlickrResult result){
		ArrayList<ImageData> images = new ArrayList<ImageData>();
		ArrayList<FlickrPhoto> photos = result.photos.photo;
		for(FlickrPhoto pic : photos){
			String thumbnailUrl = FlickrPictureUrlUtility.getFlickrThumbnail(pic);
			String imageUrl = FlickrPictureUrlUtility.getFlickrLarge(pic);
			String title = pic.title;
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("imageUrl", imageUrl);
			data.put("title", title);
			ImageData imageData = new ImageData(thumbnailUrl, data);
			images.add(imageData);
		}
		return images;
	}

}
