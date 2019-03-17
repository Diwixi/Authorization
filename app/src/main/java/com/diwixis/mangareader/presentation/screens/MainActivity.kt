package com.diwixis.mangareader.presentation.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.diwixis.mangareader.R
import com.diwixis.mangareader.utils.extensions.require
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        responseTextView.text = intent?.extras.require<String>(key = EXTRA_RESPONSE)
    }

    companion object {
        private const val EXTRA_RESPONSE = ".mangareader.extras.response"
        fun launch(context: Context, response: String) {
            val intent = Intent(context, MainActivity::class.java).apply {
                putExtra(EXTRA_RESPONSE, response)
            }
            context.startActivity(intent)
        }
    }
}