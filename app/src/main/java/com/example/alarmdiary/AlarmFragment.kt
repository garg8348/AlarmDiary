package com.example.alarmdiary

import com.example.alarmdiary.data.*
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.withContext
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
        return inflater.inflate(R.layout.fragment_alarm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnStartTime = view.findViewById(R.id.btnStartTime)
        btnEndTime = view.findViewById(R.id.btnEndTime)
        simpleTimePicker = view.findViewById(R.id.simpleTimePicker)
        spinnerFrequency = view.findViewById(R.id.spinnerFrequency)
        btnSetAlarm = view.findViewById(R.id.btnSetAlarm)

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
        val calendar = Calendar.getInstance()
        val defaultHour = if (isStartTimeSelected) 5 else 22
        val defaultMinute = 0
        simpleTimePicker.hour = defaultHour
        simpleTimePicker.minute = defaultMinute
    }

    private fun setAlarm() {
        val startTimeHour = simpleTimePicker.hour
        val startTimeMinute = simpleTimePicker.minute
        val endTimeHour = startTimeHour + 1
        val endTimeMinute = startTimeMinute

        //val frequencyInHours = spinnerFrequency.selectedItem.toString().toInt()
        val frequencyInHours = 1

        val alarm = Alarm(
            startTimeHour = startTimeHour,
            startTimeMinute = startTimeMinute,
            endTimeHour = endTimeHour,
            endTimeMinute = endTimeMinute,
            frequency = "$frequencyInHours hours"
        )

        GlobalScope.launch(Dispatchers.IO) {
            val alarmId = MyApp.getInstance().database.alarmDao().insert(alarm)
            val calendar = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, startTimeHour)
                set(Calendar.MINUTE, startTimeMinute)
                set(Calendar.SECOND, 0)
            }

            val scheduledTime = scheduleAlarm(alarmId, calendar.timeInMillis)
            withContext(Dispatchers.Main) {
                Toast.makeText(requireContext(), "Alarm set for $calendar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    suspend fun scheduleAlarm(alarmId: Long, startTimeInMillis: Long):Long {
        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(requireContext(), MyAlarmReceiver::class.java).apply {
            action = "YOUR_ALARM_ACTION" // Set a unique action string
            putExtra("ALARM_ID", alarmId)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            requireContext(),
            alarmId.toInt(),
            intent,
            PendingIntent.FLAG_IMMUTABLE // Use FLAG_IMMUTABLE instead of FLAG_UPDATE_CURRENT from 31
        )

        // Replace AlarmManager.INTERVAL_HOUR with your desired frequency
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            startTimeInMillis,
            AlarmManager.INTERVAL_HOUR,
            pendingIntent
        )
        return alarmId
    }
}