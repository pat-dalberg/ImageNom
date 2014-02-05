package com.dalberg.android.imagenom.async;

import java.io.UnsupportedEncodingException;

import org.json.JSONObject;

import retrofit.client.Response;
import retrofit.http.GET;
import android.util.Log;

import com.dalberg.android.imagenom.model.Data;
import com.dalberg.android.imagenom.url.ImgurUrlBuilder;
import com.dalberg.android.imagenom.utility.ImageNomConstants;
import com.dalberg.android.imagenom.utility.StringUtilities;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public class ImgurClient {
	
	private static final String TAG = "ImgurClient";
	
	private String mSearchTerm = null;
	
	private static final String IMGUR_BASE_URL = "https://api.imgur.com";
	private static final String IMGUR_SEARCH_FRAGMENT = "/3/gallery/search/";
	
	private Data mData;
	
	
	static class Search {
		String searchTerms;
	}
	
	
	interface Imgur {
		@GET(IMGUR_SEARCH_FRAGMENT)
		void searchImgur(@Query("q") String search, Callback<Data> cb);				
	}
	
	RequestInterceptor requestInterceptor = new RequestInterceptor(){

		@Override
		public void intercept(RequestFacade request) {
			request.addHeader("Authorization", ImageNomConstants.IMGUR_CLIENT_ID);			
		}
		
	};
	
	public void doImgurSearch(String searchTerm){
		String encodedSearch = null;
		try {
			encodedSearch = StringUtilities.makeUrlEncoded(searchTerm);
		} catch (UnsupportedEncodingException e) {
			// TODO toast encoding error
			e.printStackTrace();
			//return null;
		}
		
		RestAdapter restAdapter = new RestAdapter.Builder()
			.setEndpoint(IMGUR_BASE_URL)
			.setRequestInterceptor(requestInterceptor)
			.build();
		
		Imgur imgur = restAdapter.create(Imgur.class);
		
		Callback<Data> callback = new Callback<Data>(){
			
			@Override
			public void failure(RetrofitError error) {
				// TODO handle error
				Log.e(TAG, error.getMessage());
				return;				
			}

			@Override
			public void success(Data data, Response response) {
				Log.i(TAG, response.getReason());
				if(data != null){
					Log.i(TAG, "data = " + new Gson().toJson(data).toString());
				}
				mData = data;				
			}			
		};
		
		imgur.searchImgur(encodedSearch, callback);
		//System.out.print(mData.toString());
		//return mData;
	}

}
