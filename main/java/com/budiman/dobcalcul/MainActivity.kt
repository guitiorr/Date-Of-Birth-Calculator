package com.budiman.dobcalcul

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var textDate : TextView? = null
    private var textMinute : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = android.graphics.Color.parseColor("#498EE9")
        }

        val datePickerButton : Button = findViewById(R.id.dateBtn)
         textDate = findViewById(R.id.dateText)
        textMinute = findViewById(R.id.minutesText)

        datePickerButton.setOnClickListener{
            clickDatePicker()
//            Toast.makeText(this, "Date Picker pressed", Toast.LENGTH_SHORT).show()

        }
    }

    fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val date = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
                Toast.makeText(this, "Date Picked!", Toast.LENGTH_SHORT).show()

                val selectedDate = "$dayOfMonth/${month+1}/$year"

                val outputSelected = "👉$dayOfMonth/${month+1}/$year👈"

                textDate?.text = outputSelected

                val sdf = SimpleDateFormat("dd/MM/yyyy")

                val theDate = sdf.parse(selectedDate)

                theDate?.let{
                    val selectedDateInMinutes = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    val currentDateInMinutes = currentDate.time / 60000

                    val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                    textMinute?.text = "👉${differenceInMinutes.toString()}👈"
                }

            },
            year, month, date)

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
//        Toast.makeText(this, "Date Picker pressed", Toast.LENGTH_SHORT).show()

    }

}