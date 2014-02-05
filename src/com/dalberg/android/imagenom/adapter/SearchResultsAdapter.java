package com.dalberg.android.imagenom.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.dalberg.android.imagenom.R;
import com.dalberg.android.imagenom.model.ImageData;
import com.dalberg.android.imagenom.view.SquaredImageView;
import com.squareup.picasso.Picasso;

public class SearchResultsAdapter extends ArrayAdapter<ImageData> {
	
	private Context mContext;
	private ArrayList<ImageData> mImages;

	public SearchResultsAdapter(Context context, int resource, ArrayList<ImageData> objects) {
		super(context, resource, objects);
		mContext = context;
		mImages = objects;
		
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		SquaredImageView view = (SquaredImageView)convertView;
		if(view == null){
			view = new SquaredImageView(mContext);
		}
		String url = getItem(position).imageUrl;
		Picasso.with(mContext)
		.load(url)
		.placeholder(R.drawable.placekitten100_100)
		.into(view);
		return view;
	}

}
