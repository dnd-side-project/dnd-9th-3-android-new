package com.dnd_9th_3_android.gooding.trash.presentation.record.temp

import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetSelectCategory: BottomSheetDialogFragment() {
//    private var _binding: BottomSheetSelectCategoryBinding? = null
//    private val binding: BottomSheetSelectCategoryBinding
//        get() = _binding!!
//
//    private var selectedCategory: String = "1"
//    private var selectCategoryMap = HashMap<String, String>()
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = BottomSheetSelectCategoryBinding.inflate(inflater, container, false)
//
//        setupView()
//        setupListeners()
//
//        return binding.root
//    }
//
//    override fun getTheme(): Int = R.style.RounderBottomSheetDialog
//
//    private fun setupView() {
//        binding.cvSelectCategory.background = GradientDrawable().apply {
//            val radius = resources.getDimension(R.dimen.bottom_sheet_radius)
//            cornerRadii = floatArrayOf(radius, radius, radius, radius, 0f, 0f, 0f, 0f)
//        }
//
//        binding.btnComplete.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_background01)
//    }
//
//    private fun setupListeners() {
//        binding.ivClose.setOnClickListener {
//            dismiss()
//        }
//
////        val selectCategoryMap = HashMap<String, String>()
//
//        selectCategoryMap["쇼핑"] = "1"
//        selectCategoryMap["여행"] = "2"
//        selectCategoryMap["미식"] = "3"
//        selectCategoryMap["독서"] = "4"
//        selectCategoryMap["요리"] = "5"
//        selectCategoryMap["문화"] = "6"
//        selectCategoryMap["스포츠"] = "7"
//        selectCategoryMap["취미"] = "8"
//        selectCategoryMap["학업"] = "9"
//        selectCategoryMap["이달의 굳이데이"] = "10"
//
//        binding.cvCategory01.setOnClickListener {
//            binding.cvCategory01.strokeColor = ContextCompat.getColor(requireContext(), R.color.primaryColor)
//            binding.btnComplete.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_activated_background01)
//            binding.btnComplete.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
//
//            selectedCategory = "쇼핑"
//        }
//
//        binding.cvCategory02.setOnClickListener {
//            binding.cvCategory02.strokeColor = ContextCompat.getColor(requireContext(), R.color.primaryColor)
//            binding.btnComplete.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_activated_background01)
//            binding.btnComplete.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
//
//            selectedCategory = "여행"
//        }
//
//        binding.cvCategory03.setOnClickListener {
//            binding.cvCategory03.strokeColor = ContextCompat.getColor(requireContext(), R.color.primaryColor)
//            binding.btnComplete.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_activated_background01)
//            binding.btnComplete.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
//
//            selectedCategory = "미식"
//        }
//
//        binding.cvCategory04.setOnClickListener {
//            binding.cvCategory04.strokeColor = ContextCompat.getColor(requireContext(), R.color.primaryColor)
//            binding.btnComplete.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_activated_background01)
//            binding.btnComplete.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
//
//            selectedCategory = "독서"
//        }
//
//        binding.cvCategory05.setOnClickListener {
//            binding.cvCategory05.strokeColor = ContextCompat.getColor(requireContext(), R.color.primaryColor)
//            binding.btnComplete.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_activated_background01)
//            binding.btnComplete.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
//
//            selectedCategory = "요리"
//        }
//
//        binding.cvCategory06.setOnClickListener {
//            binding.cvCategory06.strokeColor = ContextCompat.getColor(requireContext(), R.color.primaryColor)
//            binding.btnComplete.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_activated_background01)
//            binding.btnComplete.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
//
//            selectedCategory = "문화"
//        }
//
//        binding.cvCategory07.setOnClickListener {
//            binding.cvCategory07.strokeColor = ContextCompat.getColor(requireContext(), R.color.primaryColor)
//            binding.btnComplete.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_activated_background01)
//            binding.btnComplete.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
//
//            selectedCategory = "스포츠"
//        }
//
//        binding.cvCategory08.setOnClickListener {
//            binding.cvCategory08.strokeColor = ContextCompat.getColor(requireContext(), R.color.primaryColor)
//            binding.btnComplete.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_activated_background01)
//            binding.btnComplete.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
//
//            selectedCategory = "취미"
//        }
//
//        binding.cvCategory09.setOnClickListener {
//            binding.cvCategory09.strokeColor = ContextCompat.getColor(requireContext(), R.color.primaryColor)
//            binding.btnComplete.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_activated_background01)
//            binding.btnComplete.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
//
//            selectedCategory = "학업"
//        }
//
//        binding.cvCategory10.setOnClickListener {
//            binding.cvCategory10.strokeColor = ContextCompat.getColor(requireContext(), R.color.primaryColor)
//            binding.btnComplete.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_activated_background01)
//            binding.btnComplete.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
//
//            selectedCategory = "이달의 굳이데이"
//        }
//
//        binding.btnComplete.setOnClickListener {
//            setFragmentResult(
//                REQUEST_KEY,
//                Bundle().apply {
//                    putString(Result.CATEGORY, selectedCategory)
////                    putString(BottomSheetSelectCategory.Result.CATEGORY_NUMBER, selectedCategory)
//                }
//            )
//
//            dismiss()
//        }
//    }
//
//    object Result {
//        const val CATEGORY = "category"
//        const val CATEGORY_NUMBER = "category_number"
//    }
//
//    companion object {
//        const val REQUEST_KEY = "BottomSheetSelectCategory"
//
//        fun newInstance(): BottomSheetSelectCategory =
//            BottomSheetSelectCategory().apply {
//                arguments = Bundle()
//            }
//    }
//
//    override fun onDestroyView() {
//        _binding = null
//        super.onDestroyView()
//    }
}