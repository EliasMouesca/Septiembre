package com.elicapo.yaesseptiembre

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.java


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        window.setFlags(1024, 1024);
    }

    override fun onStart() {
        val daysRemaining: Int
        val content: SpannableString?
        super.onStart()
        val now: Calendar = Calendar.getInstance()
        val esBisieto = now.get(1) % 4 == 0
        var primerDiaSeptiembre = 244
        if (esBisieto) {
            primerDiaSeptiembre = 245
        }
        daysRemaining = if (now.get(6) < primerDiaSeptiembre) {
            primerDiaSeptiembre - now.get(6)
        } else if (now.get(6) > primerDiaSeptiembre + 30) {
            (primerDiaSeptiembre + 365) - now.get(6)
        } else {
            0
        }
        SpannableString("No")
        if (daysRemaining == 0) {
            content = SpannableString("Sí")
            (findViewById<View?>(R.id.faltanDias) as TextView).text = ""
            (findViewById<View?>(R.id.dino) as ImageView).visibility = View.VISIBLE
        } else {
            val content2 = SpannableString("No")
            val withMarkup = getString(R.string.faltanDiasHtml, arrayOf<Any?>(daysRemaining))
            Intrinsics.checkNotNullExpressionValue( withMarkup, "getString(R.string.faltanDiasHtml, daysRemaining)" )
            (findViewById<View?>(R.id.faltanDias) as TextView).text = Html.fromHtml(withMarkup)
            (findViewById<View?>(R.id.dino) as ImageView).visibility = View.INVISIBLE
            content = content2
        }
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        (findViewById<View?>(R.id.siNo) as TextView).text = content
        val ids = AppWidgetManager.getInstance(application).getAppWidgetIds(
            ComponentName(
                application,
                WidgetDiasRestantes::class.java as Class<*>
            )
        )
        val myWidget: WidgetDiasRestantes = WidgetDiasRestantes()
        val appWidgetManager = AppWidgetManager.getInstance(this)
        Intrinsics.checkNotNullExpressionValue(appWidgetManager, "getInstance(this)")
        Intrinsics.checkNotNullExpressionValue(ids, "ids")
        myWidget.onUpdate(this, appWidgetManager, ids)

    }
}