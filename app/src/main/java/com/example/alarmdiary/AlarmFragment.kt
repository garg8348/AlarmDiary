package com.example.alarmdiary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.util.*

class AlarmFragment : Fragment() {

    private lateinit var btnStartTime: Button
    private lateinit var btnEndTime: Button
    private lateinit var simpleTimePicker: TimePicker
    private lateinit var spinnerFrequency: Spinner
    private lateinit var btnSetAlarm: Button

    private var isStartTimeSelected = true // Initial selection is start time

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alarm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnStartTime = view.findViewById(R.id.btnStartTime)
        btnEndTime = view.findViewById(R.id.btnEndTime)
        simpleTimePicker = view.findViewById(R.id.simpleTimePicker)
        spinnerFrequency = view.findViewById(R.id.spinnerFrequency)
        btnSetAlarm = view.findViewById(R.id.btnSetAlarm)

        // Set initial highlighting
        highlightButton(btnStartTime)

        btnStartTime.setOnClickListener {
            isStartTimeSelected = true
            highlightButton(btnStartTime)
            dimButton(btnEndTime)
            updatePickerTime()
            showStartTimeButtons()
        }

        btnEndTime.setOnClickListener {
            isStartTimeSelected = false
            highlightButton(btnEndTime)
            dimButton(btnStartTime)
            updatePickerTime()
            showEndTimeButtons()
        }

        btnSetAlarm.setOnClickListener {
            setAlarm()
        }
    }

    private fun showStartTimeButtons() {
        view?.findViewById<ImageView>(R.id.btnCheckStartTime)?.visibility = View.VISIBLE
        view?.findViewById<ImageView>(R.id.btnCancelStartTime)?.visibility = View.VISIBLE
        view?.findViewById<ImageView>(R.id.btnCheckEndTime)?.visibility = View.INVISIBLE
        view?.findViewById<ImageView>(R.id.btnCancelEndTime)?.visibility = View.INVISIBLE
    }

    private fun showEndTimeButtons() {
        view?.findViewById<ImageView>(R.id.btnCheckStartTime)?.visibility = View.INVISIBLE
        view?.findViewById<ImageView>(R.id.btnCancelStartTime)?.visibility = View.INVISIBLE
        view?.findViewById<ImageView>(R.id.btnCheckEndTime)?.visibility = View.VISIBLE
        view?.findViewById<ImageView>(R.id.btnCancelEndTime)?.visibility = View.VISIBLE
    }

    private fun highlightButton(button: Button) {
        button.alpha = 1.0f
    }

    private fun dimButton(button: Button) {
        button.alpha = 0.5f
    }

    private fun updatePickerTime() {
        // Implement logic to update TimePicker based on the selected button and any other requirements
        // For example, you can set default values if nothing is selected
        val calendar = Calendar.getInstance()
        val defaultHour = if (isStartTimeSelected) 5 else 22
        val defaultMinute = 0
        simpleTimePicker.hour = defaultHour
        simpleTimePicker.minute = defaultMinute
    }

    private fun setAlarm() {
        // Implement logic to set alarms based on the selected start time, end time, and frequency
        // You can use the values from simpleTimePicker and spinnerFrequency to schedule alarms
        Toast.makeText(requireContext(), "Alarm set!", Toast.LENGTH_SHORT).show()
    }
}