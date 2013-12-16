package com.example.tagging;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
public class CustomList extends ArrayAdapter<String>{
	private final Activity context;
	private final String[] imageId;
	public CustomList(Activity context, String[] imageId) {
		super(context, R.layout.list_single);
		this.context = context;
		this.imageId = imageId;
	}
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView= inflater.inflate(R.layout.list_single, null, true);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
		imageView.setImageURI(Uri.parse(imageId[position]));
		return rowView;
	}
	
	@Override
	public int getCount() {
		return (imageId == null) ? 0 : imageId.length;
	} 

	
}
