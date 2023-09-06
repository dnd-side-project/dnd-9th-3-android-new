package com.dnd_9th_3_android.gooding.presentation.record.temp

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import com.dnd_9th_3_android.gooding.R
import com.dnd_9th_3_android.gooding.databinding.BottomSheetDatePickerBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar

class BottomSheetDatePicker : BottomSheetDialogFragment() {
    private var _binding: BottomSheetDatePickerBinding? = null
    private val binding: BottomSheetDatePickerBinding
        get() = _binding!!

    private var selectedDate = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetDatePickerBinding.inflate(inflater, container, false)

        setupView()
        setupListeners()

        return binding.root
    }

    override fun getTheme(): Int = R.style.RounderBottomSheetDialog

    private fun setupView() {
        binding.cvDatePicker.background = GradientDrawable().apply {
            val radius = resources.getDimension(R.dimen.bottom_sheet_radius)
            cornerRadii = floatArrayOf(radius, radius, radius, radius, 0f, 0f, 0f, 0f)
        }

        binding.datePicker.maxDate = System.currentTimeMillis()

        binding.datePicker.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance().apply {
                set(year, month, dayOfMonth)
            }
            selectedDate = calendar
        }
    }

    private fun setupListeners() {
        binding.ivClose.setOnClickListener {
            dismiss()
        }

        binding.btnComplete.setOnClickListener {
            setFragmentResult(
                REQUEST_KEY,
                Bundle().apply {
                    putLong(Result.DATE, selectedDate.timeInMillis)
                }
            )

            dismiss()
        }
    }

    object Result {
        const val DATE = "date"
    }

    companion object {
        const val REQUEST_KEY = "BottomSheetDatePicker"

        fun newInstance(): BottomSheetDatePicker =
            BottomSheetDatePicker().apply {
                arguments = Bundle()
            }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}