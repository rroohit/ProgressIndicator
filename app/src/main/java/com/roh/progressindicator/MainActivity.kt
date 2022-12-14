package com.roh.progressindicator

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.roh.progressindicator.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenStarted {

            launch(Dispatchers.Main) {
                viewModel.indicatorCount.collect { count ->
                    binding.txtIndicatorCount.text = count.toString()

                }
            }

            launch(Dispatchers.Main) {
                viewModel.selectedIndicator.collect { selected ->
                    binding.txtSelectedIndicator.text = selected.toString()
                    binding.indicator.selectedIndicator = selected
                }
            }

            launch(Dispatchers.Main) {
                viewModel.indicatorRadius.collect { radius ->
                    binding.txtIndicatorRadius.text = radius.toString()
                    binding.indicator.indicatorRadius = radius

                }
            }

            launch {
                viewModel.trackerThickness.collect { thickness ->
                    binding.txtTrackerThickness.text = thickness.toString()
                    binding.indicator.trackThickness = thickness

                }
            }

        }

        binding.fbIncreaseIndicatorCount.setOnClickListener {
            val count = binding.indicator.noOfIndicators + 1
            viewModel.updateIndicatorCount(count)
        }

        binding.fbDecreaseIndicatorCount.setOnClickListener {
            val count = binding.indicator.noOfIndicators - 1
            viewModel.updateIndicatorCount(count)
        }

        binding.fbIncreaseSelectedCount.setOnClickListener {
            val selected = binding.indicator.selectedIndicator + 1
            viewModel.updateSelectedIndicator(selected)
        }

        binding.fbDecreaseSelectedCount.setOnClickListener {
            val selected = binding.indicator.selectedIndicator - 1
            viewModel.updateSelectedIndicator(selected)
        }

        binding.fbIncreaseIndicatorRadius.setOnClickListener {
            val radius = binding.indicator.indicatorRadius + 10
            viewModel.updateIndicatorRadius(radius)
        }

        binding.fbDecreaseIndicatorRadius.setOnClickListener {
            val radius = binding.indicator.indicatorRadius - 5
            viewModel.updateIndicatorRadius(radius)
        }

        binding.fbIncreaseTrackThickness.setOnClickListener {
            val thickness = binding.indicator.trackThickness + 5
            viewModel.updateTrackerThickness(thickness)

        }

        binding.fbDecreaseTrackThickness.setOnClickListener {
            val thickness = binding.indicator.trackThickness - 5
            viewModel.updateTrackerThickness(thickness)

        }


    }


}