package com.appstory.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import com.appstory.R
import com.appstory.view.liststory.ListStoryActivity
import com.appstory.widget.DisplayStoryWidget.Companion.WIDGET_ID_EXTRA

class DisplayStoryWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action == WIDGET_CLICK) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val views = RemoteViews(context.packageName, R.layout.display_story_widget)
            val appWidgetId = intent.getIntExtra(WIDGET_ID_EXTRA, 0)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    companion object {
        const val WIDGET_CLICK = "android.appwidget.action.APPWIDGET_UPDATE"
        const val WIDGET_ID_EXTRA = "widget_id_extra"
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val intent = Intent(context, ListStoryActivity::class.java)
    intent.action = DisplayStoryWidget.WIDGET_CLICK
    intent.putExtra(WIDGET_ID_EXTRA, appWidgetId)

    val pendingIntent = PendingIntent.getActivity(
        context, appWidgetId, intent,
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
    )

    val views = RemoteViews(context.packageName, R.layout.display_story_widget)
    views.setOnClickPendingIntent(R.id.btn_click, pendingIntent)

    appWidgetManager.updateAppWidget(appWidgetId, views)
}