package com.dalberg.android.imagenom.event;

import com.dalberg.android.imagenom.model.ImageData;

public class ImageSelectionEvent {
	
	public String imageUrl;
	public String strTitle;
	
	public ImageSelectionEvent(ImageData data){
		this.imageUrl = data.imageData.get("imageUrl");
		this.strTitle = data.imageData.get("title");
	}

}
