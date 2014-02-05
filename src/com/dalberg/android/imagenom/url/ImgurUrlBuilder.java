package com.dalberg.android.imagenom.url;

public class ImgurUrlBuilder extends AbstractUrlBuilder {
	
	private static final String BASE_URL = "https://api.imgur.com";
	private static final String IMGUR_SEARCH_FRAGMENT = "/3/gallery/search/time/?q={search}";

	@Override
	public String getSearchUrl(String searchTerm) {
		StringBuilder sb = new StringBuilder(BASE_URL)
		.append("gallery/search/time/?q=")
		.append(searchTerm);
		return sb.toString();
	}
	
	public static String getImgurBaseUrl(){
		return BASE_URL;
	}
	
	public static String getImgurSearchFragment(){
		return IMGUR_SEARCH_FRAGMENT;
	}

}
