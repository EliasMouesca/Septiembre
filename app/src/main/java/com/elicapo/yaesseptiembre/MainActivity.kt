package com.elicapo.yaesseptiembre

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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        window.setFlags(1024, 1024)
    }

    override fun onStart() {
        super.onStart()

        val daysRemaining = daysUntilSeptember(Calendar.getInstance())

        val faltanDias = findViewById<TextView>(R.id.faltanDias)
        val dino = findViewById<ImageView>(R.id.dino)
        val siNo = findViewById<TextView>(R.id.siNo)

        val content = if (daysRemaining == 0) {
            faltanDias.text = ""
            dino.visibility = View.VISIBLE
            SpannableString("Sí")
        } else {
            val withMarkup = getString(R.string.faltanDiasHtml, daysRemaining)
            faltanDias.text = Html.fromHtml(
                withMarkup,
                Html.FROM_HTML_MODE_LEGACY
            )
            dino.visibility = View.INVISIBLE
            SpannableString("No")
        }

        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        siNo.text = content
    }
}
