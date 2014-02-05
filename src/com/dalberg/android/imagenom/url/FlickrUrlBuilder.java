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

package com.dalberg.android.imagenom.url;

import com.dalberg.android.imagenom.utility.ImageNomConstants;

public class FlickrUrlBuilder extends AbstractUrlBuilder {

	private static String BASE_URL = "http://api.flickr.com/services/rest/?method=flickr.photos.getRecent&api_key=%s&extras=tags=%s&format=json&nojsoncallback=1";
	
	@Override
	public String getSearchUrl(String searchTerm) {
		StringBuilder sb = new StringBuilder(String.format(BASE_URL, ImageNomConstants.FLICKR_KEY, searchTerm));
		return sb.toString();
	}

}
