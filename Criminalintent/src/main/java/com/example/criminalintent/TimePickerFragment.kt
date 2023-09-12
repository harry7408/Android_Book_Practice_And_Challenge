package com.example.criminalintent

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import java.util.*

private const val ARG_TIME="time"

class TimePickerFragment : DialogFragment() {

    interface Callback {
        fun onTimeSelected(date: Date)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val timeListener =
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                val resultTime: Date = GregorianCalendar(
                    Calendar.YEAR,
                    Calendar.MONTH,
                    Calendar.DAY_OF_MONTH,
                    hourOfDay,
                    minute
                ).time

                targetFragment?.let { fragment: Fragment ->
                    (fragment as Callback).onTimeSelected(resultTime)
                }
            }
        val time = arguments?.getSerializable(ARG_TIME) as Date
        val calendar = Calendar.getInstance()
        calendar.time = time
        val hour=calendar.get(Calendar.HOUR)
        val minute=calendar.get(Calendar.MINUTE)


        return TimePickerDialog(
            requireContext(),
            timeListener,
            hour,
            minute,
            false
        )
    }

    companion object {
        fun newInstance(date: Date): TimePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_TIME, date)
            }
            return TimePickerFragment().apply {
                arguments = args
            }
        }
    }
}