package com.aukix.jcdelvalle.itesmlogos;

import android.app.Activity;
import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.Toast;

public class Widget_4x1_Config extends Activity{
	
	private static final String TAG = "WIDGET_4x1_CONFIGURATION";
	private Gallery gallery;
	Context context = Widget_4x1_Config.this;
	int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
	Intent i;
	
	public Widget_4x1_Config() {
		super();
    }
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setResult(RESULT_CANCELED); // In case of BackButton pressed then cancel the widget
		setContentView(R.layout.widget_4x1_config);
	
		//**** GALLLERY ****//
		gallery = (Gallery) findViewById(R.id.gallery);
	    gallery.setAdapter(new ImageAdapter(this));
	    //*****************//
		
	 // Identifying Widget ID with which to interact
	    Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            appWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);
            Log.d(TAG, "mAppWidgetId: "+appWidgetId);
        }
        
        // If they gave us an intent without the widget id, just bail.
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }
        
        // SETTING SIZE
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_4x1);
        AppWidgetHostView awhv = new AppWidgetHostView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(300, 50);
        awhv.setLayoutParams(params);
        AppWidgetManager.getInstance( this ).updateAppWidget(getCallingActivity(), remoteViews);
        
        
        
        
        gallery.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView parent, View v, int position, long id) {
	            Toast.makeText(Widget_4x1_Config.this, "" + position, Toast.LENGTH_SHORT).show();
	            
	            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
				Widget_4x1.updateAppWidget(context, appWidgetManager, appWidgetId, position);
				
				Intent resultValue = new Intent();
				resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
				setResult(RESULT_OK, resultValue);
				finish();
	        }
	    });
	}
	
	public class ImageAdapter extends BaseAdapter {
	    int mGalleryItemBackground;
	    private Context mContext;
	    
	    private int[] LOGOTYPES = { 
			R.drawable.borregoscem_white, 
			R.drawable.tec_monterrey, 
			R.drawable.ic_borregoscem, 
			R.drawable.ic_itesm};
	    
	    public ImageAdapter(Context c) {
	        mContext = c;
	        TypedArray attr = mContext.obtainStyledAttributes(R.styleable.HelloGallery);
	        mGalleryItemBackground = attr.getResourceId(R.styleable.HelloGallery_android_galleryItemBackground, 0);
	        attr.recycle();
	    }

	    public int getCount() {
	        return LOGOTYPES.length;
	    }

	    public Object getItem(int position) {
	        return position;
	    }

	    public long getItemId(int position) {
	        return position;
	    }

	    public View getView(int position, View convertView, ViewGroup parent) {
	        ImageView imageView = new ImageView(mContext);

	        imageView.setImageResource(LOGOTYPES[position]);
	        imageView.setLayoutParams(new Gallery.LayoutParams(300, 300));
	        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
	        imageView.setBackgroundResource(mGalleryItemBackground);

	        return imageView;
	    }
	}
}
