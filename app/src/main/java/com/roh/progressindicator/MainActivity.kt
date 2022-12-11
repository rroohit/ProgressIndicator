package com.roh.progressindicator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.roh.progressindicator.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenStarted {
            launch(Dispatchers.Main) {
                updateProgressIndicator(1)
            }
        }

    }

    private suspend fun updateProgressIndicator(next: Int) {
        delay(2000)
        if (next <= 7) {
            binding.indicator.selectedIndicator = next + 1
            updateProgressIndicator( next + 1)

        } else {
            binding.indicator.selectedIndicator = 1
        }
    }


}