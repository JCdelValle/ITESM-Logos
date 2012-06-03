package com.aukix.jcdelvalle.itesmlogos;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;

public class Widget_4x1 extends AppWidgetProvider {
	
	private static final String TAG = "WIDGET_4x1";
	int position = 0;
	private static final int[] IMAGES = { 
		R.drawable.borregoscem_white, 
		R.drawable.tec_monterrey, 
		R.drawable.ic_borregoscem, 
		R.drawable.ic_itesm};
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
		final int N = appWidgetIds.length;
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];  
            Log.d(TAG, "appWidgetId: "+appWidgetId);
            updateAppWidget(context, appWidgetManager, appWidgetId, position);
        }
	}
	
	public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,int appWidgetId, int position){
			RemoteViews updateWidget = new RemoteViews(context.getPackageName(), R.layout.widget_4x1);
			updateWidget.setImageViewResource(R.id.iv_logo,IMAGES[position]);
			appWidgetManager.updateAppWidget(appWidgetId, updateWidget);

	}

}
