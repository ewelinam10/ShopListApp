package com.example.ewmysiak.shoplistapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.widget.RemoteViews;

import com.example.ewmysiak.shoplistapp.Helpers.DataBaseHelper;
import com.example.ewmysiak.shoplistapp.ListObjects.ShopListAdapter;
import com.example.ewmysiak.shoplistapp.Objects.ShopList;

/**
 * Implementation of App Widget functionality.
 */
public class ShopListWidget extends AppWidgetProvider {
    Context context;
    private ShopListAdapter adapter;
    RecyclerView recyclerView;


    private void getList() {
        DataBaseHelper dbHelper = new DataBaseHelper(context);
       // adapter = new ShopListAdapter(dbHelper.getLists(),this);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.shop_list_widget);

        appWidgetManager.updateAppWidget(new ComponentName(context,ShopListWidget.class),views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("http://www.ewelinamysiak.com"));
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
            RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.shop_list_widget);
            views.setOnClickPendingIntent(R.id.button2,pendingIntent);
            views.setRemoteAdapter(R.id.widgetListView,);
            //CharSequence widgetText = context.getString(R.string.appwidget_text);
            // views.setTextViewText(R.id.appwidget_text, widgetText);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
        super.onUpdate(context,appWidgetManager,appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

