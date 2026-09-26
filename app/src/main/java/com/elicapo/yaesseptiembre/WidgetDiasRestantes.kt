package com.elicapo.yaesseptiembre

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.view.View
import android.widget.RemoteViews
import java.util.Calendar
import kotlin.jvm.internal.Intrinsics


/**
 * Implementation of App Widget functionality.
 */
class WidgetDiasRestantes : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget( context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int ) {
    val daysRemaining: Int
    Intrinsics.checkNotNullParameter(context, "context")
    Intrinsics.checkNotNullParameter(appWidgetManager, "appWidgetManager")
    val views = RemoteViews(context.packageName, R.layout.widget_dias_restantes)
    val now: Calendar = Calendar.getInstance()
    val esBisieto = now.get(1) % 4 == 0
    var primerDiaSeptiembre = 244
    if (esBisieto) {
        primerDiaSeptiembre = 245
    }
    daysRemaining = if (now.get(6) < primerDiaSeptiembre) {
        primerDiaSeptiembre - now.get(6)
    } else if (now.get(6) > primerDiaSeptiembre + 29) {
        (primerDiaSeptiembre + 365) - now.get(6)
    } else {
        0
    }
    if (daysRemaining == 0) {
        views.setImageViewResource(R.id.fotoWidget, R.drawable.imagenseptiembre)
        views.setViewVisibility(R.id.septiembre, View.VISIBLE)
        views.setViewVisibility(R.id.faltanTV, android.view.View.INVISIBLE)
        views.setViewVisibility(R.id.remainingTV, View.INVISIBLE)
        views.setViewVisibility(R.id.paraSepTV, View.INVISIBLE)
    } else {
        views.setImageViewResource(R.id.fotoWidget, R.drawable.estilo_marco)
        views.setViewVisibility(R.id.septiembre, View.INVISIBLE)
        views.setTextViewText(R.id.remainingTV, daysRemaining.toString())
        views.setViewVisibility(R.id.faltanTV, android.view.View.VISIBLE)
        views.setViewVisibility(R.id.remainingTV, View.VISIBLE)
        views.setViewVisibility(R.id.paraSepTV, android.view.View.VISIBLE)
    }
    appWidgetManager.updateAppWidget(appWidgetId, views)
}