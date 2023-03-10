package com.example.sampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.masum.datepicker.MonthPicker
import com.masum.datepicker.MonthType
import com.masum.datepicker.listener.DateMonthDialogListener
import com.masum.datepicker.listener.OnCancelMonthDialogListener
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var monthPicker: MonthPicker
    var currentdate = Calendar.getInstance().apply {
        set(Calendar.MONTH, 10)
        set(Calendar.YEAR, 2020)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btnMonth = findViewById<Button>(R.id.btn_month)
        btnMonth.setOnClickListener {
            monthPicker.setSelectedMonth(currentdate.get(Calendar.MONTH))
            monthPicker.setSelectedYear(currentdate.get(Calendar.YEAR))
            monthPicker.show(currentdate.get(Calendar.MONTH), currentdate.get(Calendar.YEAR))
        }
        callMonthPicker()
    }


    fun callMonthPicker() {
        monthPicker = MonthPicker(this)
        monthPicker.setMonthType(MonthType.NUMBER)
        monthPicker.setPositiveButton(object : DateMonthDialogListener {
            override fun onDateMonth(
                month: Int,
                startDate: Int,
                endDate: Int,
                year: Int,
                monthLabel: String?
            ) {
                Log.e("data", month.toString() + " " + year.toString())
                currentdate = Calendar.getInstance().apply { set(year, month, 1) }
            }
        })

        monthPicker.setNegativeButton(object : OnCancelMonthDialogListener {
            override fun onCancel(dialog: AlertDialog?) {
                monthPicker.dismiss()
            }

        })
        monthPicker.setLimitDate(
            newBeginDate = Calendar.getInstance().apply {
                set(Calendar.YEAR, 2020)
                set(Calendar.MONTH, 4)
            }.time,
            newEndDate = Calendar.getInstance().apply {
                set(Calendar.YEAR, 2025)
                set(Calendar.MONTH, 8)
            }.time
        )
    }
}