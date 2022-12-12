package com.roh.indicator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.widget.ProgressBar
import com.roh.progress.indicator.R
import kotlin.math.abs

class ProgressIndicator : ProgressBar {

    //To use default from xml input
    private var _noOfIndicators: Int = 2
    private var _trackBackgroundColor: Int = Color.GRAY
    private var _trackerProgressColor: Int = Color.BLACK
    private var _selectedIndicator: Int = 1
    private var _trackThickness: Int = 20
    private var _indicatorRadius: Int = 30

    /**
     * Number of indicators on progress bar. >= 2
     */
    var noOfIndicators: Int
        get() = _noOfIndicators
        set(value) {
            _noOfIndicators = value
            invalidate()
        }


    /**
     * Set color track background.
     */
    var trackBackgroundColor: Int
        get() = _trackBackgroundColor
        set(value) {
            _trackBackgroundColor = value
        }

    /**
     * Set progress track color.
     */
    var trackerProgressColor: Int
        get() = _trackerProgressColor
        set(value) {
            _trackerProgressColor = value
        }


    /**
     * Set selected indicator
     */
    var selectedIndicator: Int
        get() = _selectedIndicator
        set(value) {
            _selectedIndicator = if (value <= 0) {
                1
            } else if (value > noOfIndicators) {
                noOfIndicators

            } else {
                invalidate()
                value
            }

        }

    /**
     * Set track thickness
     */
    var trackThickness: Int
        get() = _trackThickness
        set(value) {
            _trackThickness = value
            invalidate()
        }

    /**
     * Set track thickness must be >= 2X of tracker thickness.
     */
    var indicatorRadius: Int
        get() = _indicatorRadius
        set(value) {
            _indicatorRadius = value
            invalidate()
        }

    private val paintIndicator = Paint()
    private val trackerBackground = Paint()
    private val trackerFilled = Paint()

    var count = 0
    init {

        paintIndicator.isAntiAlias = true
        paintIndicator.color = trackBackgroundColor
        paintIndicator.style = Paint.Style.FILL

        trackerBackground.color = trackBackgroundColor
        trackerBackground.style = Paint.Style.FILL

        trackerFilled.color = trackerProgressColor
        trackerFilled.style = Paint.Style.FILL

    }


    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    //Initializing default parameters for progress indicators
    private fun init(attrs: AttributeSet?, defStyle: Int) {
        val attr = context.obtainStyledAttributes(
            attrs, R.styleable.ProgressIndicator, defStyle, 0
        )

        val indicatorCount = attr.getInt(
            R.styleable.ProgressIndicator_noOfIndicators, 2
        )
        _noOfIndicators = if (indicatorCount <= 1) 2 else indicatorCount

        val indicatorSelected = attr.getInt(
            R.styleable.ProgressIndicator_selectedIndicator, 1
        )

        _selectedIndicator = if (indicatorSelected <= 1) {
            1
        } else if (indicatorSelected >= noOfIndicators) {
            noOfIndicators
        } else {
            indicatorSelected
        }

        _trackThickness = attr.getInt(
            R.styleable.ProgressIndicator_trackThickNess, 20
        )

        _indicatorRadius = attr.getInt(
            R.styleable.ProgressIndicator_indicatorRadius, 30
        )

        _trackBackgroundColor = attr.getColor(
            R.styleable.ProgressIndicator_trackerColor, Color.GRAY
        )

        _trackerProgressColor = attr.getColor(
            R.styleable.ProgressIndicator_trackerProgressColor, Color.BLACK
        )

        attr.recycle()

    }


    override fun onDraw(canvas: Canvas) {
        count++
        Log.d("PROGRESS_INDICATOR", "onDraw: count => $count")

        trackerBackground.color = trackBackgroundColor
        trackerFilled.color = trackerProgressColor

        //Set tracker thickness
        val thickness = trackThickness.toFloat()

        ///
        val yCenter = height / 2F
        val circleRadius =
            if (indicatorRadius.toFloat() < thickness) thickness * 2 else indicatorRadius.toFloat()
        val widthMargin = width - (circleRadius * 2)

        //Depending on number of indicators has to show need to calculate difference between each indicator
        val positionDiff = ((widthMargin) / (noOfIndicators - 1))


        val left = circleRadius                          //from left axis
        val top = abs((height / 2) - (thickness / 2)) //from top point
        val bottom = top + (thickness)                   //to bottom
        val right = width - circleRadius                 //to left width of tracker

        //Background tracker
        canvas.drawRect(left, top, right, bottom, trackerBackground)

        //Completed progress
        val length = (positionDiff * (selectedIndicator - 1))
        canvas.drawRect(left, top, length + (circleRadius / 2), bottom, trackerFilled)


        //To draw each indicator
        for (indicatorIndex in 1..noOfIndicators) {

            //To fill selected indicator color
            paintIndicator.color =
                if (indicatorIndex <= selectedIndicator) trackerProgressColor else trackBackgroundColor

            //Setting up each indicator position
            val position = (positionDiff * (indicatorIndex - 1))


            //Drawing indicators
            canvas.drawCircle(
                position + circleRadius,
                yCenter,
                circleRadius,
                paintIndicator
            )

        }

    }

    //Default tracker of progress bar has to null to draw custom one
    override fun setProgressDrawable(d: Drawable?) {
        super.setProgressDrawable(null)
    }

    //Stop indeterminate progress behaviour
    override fun setIndeterminate(indeterminate: Boolean) {
        super.setIndeterminate(false)
    }

    override fun setMaxHeight(maxHeight: Int) {
        super.setMaxHeight(100)
    }

    override fun setMin(min: Int) {
        super.setMin(30)
    }

    //Handle default progress... has to scale with completed indicators...
    override fun setProgress(progress: Int) {
        super.setProgress(0)
    }

}