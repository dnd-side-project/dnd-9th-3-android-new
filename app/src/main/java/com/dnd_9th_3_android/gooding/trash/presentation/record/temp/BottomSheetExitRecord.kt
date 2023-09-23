package com.dnd_9th_3_android.gooding.trash.presentation.record.temp

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.dnd_9th_3_android.gooding.R
import com.dnd_9th_3_android.gooding.databinding.BottomSheetExitRecordBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetExitRecord: BottomSheetDialogFragment() {
    private var _binding: BottomSheetExitRecordBinding? = null
    private val binding: BottomSheetExitRecordBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetExitRecordBinding.inflate(inflater, container, false)

        setupView()
        setupListeners()

        return binding.root
    }

    override fun getTheme(): Int = R.style.RounderBottomSheetDialog

    private fun setupView() {
        binding.cvExitRecord.background = GradientDrawable().apply {
            val radius = resources.getDimension(R.dimen.bottom_sheet_radius)
            cornerRadii = floatArrayOf(radius, radius, radius, radius, 0f, 0f, 0f, 0f)
        }

        binding.btnExit.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_background02)
    }

    private fun setupListeners() {
        binding.ivClose.setOnClickListener {
            dismiss()
        }

        binding.btnExit.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        fun newInstance(): BottomSheetExitRecord =
            BottomSheetExitRecord().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}