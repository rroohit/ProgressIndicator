package com.roh.progressindicator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _indicatorCount = MutableStateFlow(2)
    val indicatorCount : StateFlow<Int> = _indicatorCount

    private val _selectedIndicator = MutableStateFlow(1)
    val selectedIndicator : StateFlow<Int> = _selectedIndicator

    private val _indicatorRadius = MutableStateFlow(35)
    val indicatorRadius: StateFlow<Int> = _indicatorRadius

    private val _trackerThickness = MutableStateFlow(15)
    val trackerThickness: StateFlow<Int> = _trackerThickness


    fun updateIndicatorCount(count: Int) {
        viewModelScope.launch {
            if (count >= 2) {
                _indicatorCount.emit(count)

            }

            if (selectedIndicator.value > count) {
                _selectedIndicator.emit(count)
            }
        }
    }

    fun updateSelectedIndicator(selected: Int) {
        viewModelScope.launch {
            if (selected <= indicatorCount.value && selected >= 1) {
                _selectedIndicator.emit(selected)
            }
        }
    }

    fun updateIndicatorRadius(radius: Int) {
        viewModelScope.launch {
            if (radius >= 20) {
                _indicatorRadius.emit(radius)
            } else {
                _indicatorRadius.emit(20)
            }

        }
    }

    fun updateTrackerThickness(thickness: Int) {
        viewModelScope.launch {
            if (thickness >= 15) {
                _trackerThickness.emit(thickness)
            } else {
                _trackerThickness.emit(15)
            }

        }
    }

}