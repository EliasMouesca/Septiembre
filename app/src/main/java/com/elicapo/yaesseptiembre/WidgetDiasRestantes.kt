package com.elicapo.yaesseptiembre

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.view.View
import android.widget.RemoteViews
import androidx.core.content.res.ResourcesCompat
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

internal fun updateAllWidgets(context: Context) {
    val appWidgetManager = AppWidgetManager.getInstance(context)
    val appWidgetIds = appWidgetManager.getAppWidgetIds(
        ComponentName(context, WidgetDiasRestantes::class.java)
    )

    appWidgetIds.forEach { appWidgetId ->
        updateAppWidget(context, appWidgetManager, appWidgetId)
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val views = RemoteViews(context.packageName, R.layout.widget_dias_restantes)
    val daysRemaining = daysUntilSeptember(Calendar.getInstance())
    views.setImageViewBitmap(R.id.widgetTextImage, createWidgetTextBitmap(context, daysRemaining))
    views.setViewVisibility(R.id.widgetTextImage, View.VISIBLE)
    views.setViewVisibility(R.id.septiembre, View.INVISIBLE)
    views.setViewVisibility(R.id.faltanTV, View.INVISIBLE)
    views.setViewVisibility(R.id.remainingTV, View.INVISIBLE)
    views.setViewVisibility(R.id.paraSepTV, View.INVISIBLE)

    if (daysRemaining == 0) {
        views.setImageViewResource(R.id.fotoWidget, R.drawable.imagenseptiembre)
    } else {
        views.setImageViewResource(R.id.fotoWidget, R.drawable.estilo_marco)
    }
    appWidgetManager.updateAppWidget(appWidgetId, views)
}

private fun createWidgetTextBitmap(context: Context, daysRemaining: Int): Bitmap {
    val bitmap = Bitmap.createBitmap(720, 420, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    val typeface = requireNotNull(ResourcesCompat.getFont(context, R.font.patrick_hand_regular))
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = context.getColor(R.color.black)
        textAlign = Paint.Align.CENTER
        this.typeface = typeface
    }

    if (daysRemaining == 0) {
        paint.textSize = 96f
        canvas.drawText(context.getString(R.string.septiembre), 360f, 230f, paint)
    } else {
        paint.textSize = 64f
        canvas.drawText(
            context.resources.getQuantityString(R.plurals.widget_faltan, daysRemaining),
            360f,
            120f,
            paint
        )
        paint.textSize = 112f
        paint.typeface = Typeface.create(typeface, Typeface.BOLD)
        canvas.drawText(daysRemaining.toString(), 360f, 235f, paint)
        paint.textSize = 64f
        paint.typeface = typeface
        canvas.drawText(
            context.resources.getQuantityString(
                R.plurals.widget_para_septiembre,
                daysRemaining
            ),
            360f,
            340f,
            paint
        )
    }

    return bitmap
}
