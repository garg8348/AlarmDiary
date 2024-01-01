package com.example.alarmdiary
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.TimePicker
import androidx.appcompat.widget.AppCompatSpinner
import com.google.android.material.button.MaterialButton

class AlarmFragment : Fragment() {

    private lateinit var timePickerStartTime: TimePicker
    private lateinit var timePickerEndTimePicker: TimePicker
    private lateinit var spinnerFrequency: AppCompatSpinner
    private lateinit var btnSetAlarm: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_alarm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize your views using findViewById
        timePickerStartTime = view.findViewById(R.id.timePickerStartTime)
        timePickerEndTimePicker = view.findViewById(R.id.timePickerEndTime)
        spinnerFrequency = view.findViewById(R.id.spinnerFrequency)
        btnSetAlarm = view.findViewById(R.id.btnSetAlarm)

        // Now you can use these views as usual
        btnSetAlarm.setOnClickListener {
            // Handle button click
        }
    }
}
