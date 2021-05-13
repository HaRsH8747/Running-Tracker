package com.example.runningtracker.other

import android.content.Context
import android.widget.TextView
import com.example.runningtracker.R
import com.example.runningtracker.db.Run
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import java.text.SimpleDateFormat
import java.util.*

class CustomMarkerView(
    val runs: List<Run>,
    c: Context,
    layoutId: Int
): MarkerView(c, layoutId) {

    override fun getOffset(): MPPointF {
        return MPPointF(-width / 2F, -height.toFloat())

    }

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        super.refreshContent(e, highlight)
        if (e == null) {
            return
        }
        val curRunId = e.x.toInt()
        val run = runs[curRunId]

        val calendar = Calendar.getInstance().apply {
            timeInMillis = run.timestamp
        }
        val dateFormat = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
        val tvDate = findViewById<TextView>(R.id.tvDate)
        tvDate.text = dateFormat.format(calendar.time)

        val avgSpeed = "${run.avgSpeedInKMH}Km/h"
        val tvAvgSpeed = findViewById<TextView>(R.id.tvAvgSpeed)
        tvAvgSpeed.text = avgSpeed

        val distanceInKm = "${run.distanceInMeters / 1000F}Km"
        val tvDistance = findViewById<TextView>(R.id.tvDistance)
        tvDistance.text = distanceInKm

        val tvDuration = findViewById<TextView>(R.id.tvDuration)
        tvDuration.text = TrackingUtility.getFormattedStopWatchTime(run.timeInMillis)

        val caloriesBurned = "${run.caloriesBurned}kcal"
        val tvCaloriesBurned = findViewById<TextView>(R.id.tvCaloriesBurned)
        tvCaloriesBurned.text = caloriesBurned
    }
}