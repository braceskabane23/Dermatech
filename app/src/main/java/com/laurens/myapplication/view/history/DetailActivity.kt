package com.laurens.myapplication.view.history

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.load
import com.laurens.myapplication.R
import com.laurens.myapplication.databinding.ActivityDetailBinding
import java.text.NumberFormat

class DetailActivity : AppCompatActivity() {
    private lateinit var date: String
    private lateinit var location: String
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        show()
        setupListeners()

    }

    private fun setupListeners() {
        binding.apply {
            tvLabel.setOnClickListener { searchOnGoogle() }

        }
    }

    private fun searchOnGoogle() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=${binding.tvLabel.text}"))
        startActivity(intent)
    }

    private fun show() {
        // Get data from intent
        val imageUri = intent.getStringExtra("imageUri")
        val label = intent.getStringExtra("label")
        val score = intent.getFloatExtra("score", 0f)
        date = intent.getStringExtra("date") ?: ""
        location = intent.getStringExtra("location") ?: ""

        // Load data into views
        binding.resultText.text = getString(R.string.hasil_analisis)
        binding.tvLabel.text = getString(R.string.analysis_type, label)
        binding.ada.text = getString(R.string.moreInfo)
        binding.tvScore.text = getString(R.string.analysis_score, NumberFormat.getPercentInstance().format(score).toString())
        binding.imageView.load(imageUri)
        binding.tvDateTime.text = date
        binding.tvLocation.text = location
    }
}