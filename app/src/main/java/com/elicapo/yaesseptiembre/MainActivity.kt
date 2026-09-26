package com.elicapo.yaesseptiembre

import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.elicapo.yaesseptiembre.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        WindowCompat.getInsetsController(window, window.decorView)
            .hide(WindowInsetsCompat.Type.statusBars())
    }

    override fun onStart() {
        super.onStart()

        val daysRemaining = daysUntilSeptember(Calendar.getInstance())

        val content = if (daysRemaining == 0) {
            binding.faltanDias.text = null
            binding.dino.visibility = View.VISIBLE
            SpannableString(getString(R.string.si))
        } else {
            val withMarkup = resources.getQuantityString(
                R.plurals.faltan_dias,
                daysRemaining,
                daysRemaining
            )
            binding.faltanDias.text = Html.fromHtml(
                withMarkup,
                Html.FROM_HTML_MODE_LEGACY
            )
            binding.dino.visibility = View.INVISIBLE
            SpannableString(getString(R.string.no))
        }

        content.setSpan(
            UnderlineSpan(),
            0,
            content.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.siNo.text = content
    }
}
