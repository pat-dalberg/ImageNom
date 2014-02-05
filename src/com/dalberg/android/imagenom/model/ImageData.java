package com.dalberg.android.imagenom.model;

import java.util.HashMap;

public class ImageData {
	
	public String imageUrl;
	public HashMap<String, String> imageData;
	
	public ImageData(String url, HashMap<String, String> data){
		imageUrl = url;
		imageData = data;
	}

}
