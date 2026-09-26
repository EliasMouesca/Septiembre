package com.elicapo.yaesseptiembre

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.view.View
import android.widget.RemoteViews
import java.util.Calendar
class WidgetDiasRestantes : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val views = RemoteViews(context.packageName, R.layout.widget_dias_restantes)
    val daysRemaining = daysUntilSeptember(Calendar.getInstance())

    if (daysRemaining == 0) {
        views.setImageViewResource(R.id.fotoWidget, R.drawable.imagenseptiembre)
        views.setViewVisibility(R.id.septiembre, View.VISIBLE)
        views.setViewVisibility(R.id.faltanTV, View.INVISIBLE)
        views.setViewVisibility(R.id.remainingTV, View.INVISIBLE)
        views.setViewVisibility(R.id.paraSepTV, View.INVISIBLE)
    } else {
        views.setImageViewResource(R.id.fotoWidget, R.drawable.estilo_marco)
        views.setViewVisibility(R.id.septiembre, View.INVISIBLE)
        views.setTextViewText(R.id.remainingTV, daysRemaining.toString())
        views.setViewVisibility(R.id.faltanTV, View.VISIBLE)
        views.setViewVisibility(R.id.remainingTV, View.VISIBLE)
        views.setViewVisibility(R.id.paraSepTV, View.VISIBLE)
    }
    appWidgetManager.updateAppWidget(appWidgetId, views)
}
