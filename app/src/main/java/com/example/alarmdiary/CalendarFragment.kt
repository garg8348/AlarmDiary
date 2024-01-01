package com.example.alarmdiary
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import androidx.recyclerview.widget.RecyclerView

class CalendarFragment : Fragment() {

    private lateinit var calendarView: CalendarView
    private lateinit var editTextTaskList: TextInputEditText
    private lateinit var btnSaveTaskList: MaterialButton
    private lateinit var recyclerViewDiary: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize your views using findViewById
        calendarView = view.findViewById(R.id.calendarView)
        editTextTaskList = view.findViewById(R.id.editTextTaskList)
        btnSaveTaskList = view.findViewById(R.id.btnSaveTaskList)
        recyclerViewDiary = view.findViewById(R.id.recyclerViewDiary)

        // Now you can use these views as usual
        btnSaveTaskList.setOnClickListener {
            // Handle button click
        }
    }
}
