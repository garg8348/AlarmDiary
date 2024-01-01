package com.example.alarmdiary
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.appcompat.widget.AppCompatSpinner
import com.google.android.material.button.MaterialButton

class AlarmFragment : Fragment() {

    private lateinit var seekBarStartTime: AppCompatSeekBar
    private lateinit var seekBarEndTime: AppCompatSeekBar
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
        seekBarStartTime = view.findViewById(R.id.seekBarStartTime)
        seekBarEndTime = view.findViewById(R.id.seekBarEndTime)
        spinnerFrequency = view.findViewById(R.id.spinnerFrequency)
        btnSetAlarm = view.findViewById(R.id.btnSetAlarm)

        // Now you can use these views as usual
        btnSetAlarm.setOnClickListener {
            // Handle button click
        }
    }
}
