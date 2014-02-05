package com.dalberg.android.imagenom.async;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.QueryMap;

import com.dalberg.android.imagenom.bus.BusProvider;
import com.dalberg.android.imagenom.event.FlickrEvent;
import com.dalberg.android.imagenom.model.FlickrResult;
import com.dalberg.android.imagenom.utility.StringUtilities;
import com.squareup.otto.Produce;

public class FlickrClient {
	
	private static final String TAG = "FlickrClient";
	
	private static final String FLICKR_ENDPOINT = "https://api.flickr.com/services/rest";
	private static final String FLICKR_QUERY = "flickr.photos.search";
	
	private static Map<String, String> options = new HashMap<String, String>();
	
	static {
		options.put("method","flickr.photos.search");
		options.put("api_key", "8ca2bf66d4ebcce9042bb0cca81f906b");
		options.put("format", "json");
		options.put("nojsoncallback", "1");
	}
	
	static class Search {
		String searchTerms;
	}
	
	interface Flickr {
		@GET("/")
		void searchFlickr(@QueryMap Map<String, String> options, Callback<FlickrResult> cb);
	}
	
	public void doFlickrSearch(String searchTerm){
		String encodedSearch = null;
		try {
			encodedSearch = StringUtilities.makeUrlEncoded(searchTerm);
		} catch (UnsupportedEncodingException e) {
			// TODO toast encoding error
			e.printStackTrace();
			//return null;
		}
		
		RestAdapter restAdapter = new RestAdapter.Builder()
			.setEndpoint(FLICKR_ENDPOINT)
			.build();
		
		Flickr flickr = restAdapter.create(Flickr.class);
		
		Callback<FlickrResult> callback = new Callback<FlickrResult>(){

			@Override
			public void failure(RetrofitError error) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void success(FlickrResult flickrResult, Response response) {
				BusProvider.getInstance().post(produceFlickrEvent(flickrResult));
			}
			
		};
		
		options.put("text", encodedSearch);
		flickr.searchFlickr(options, callback);
	}
	
	@Produce
	public FlickrEvent produceFlickrEvent(FlickrResult flickrResult){
		return new FlickrEvent(flickrResult);
	}
	
}
