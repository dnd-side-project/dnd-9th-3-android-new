package com.dnd_9th_3_android.gooding.trash

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.dnd_9th_3_android.gooding.MainActivity
import com.dnd_9th_3_android.gooding.databinding.FragmentMyGoodingBinding
import com.dnd_9th_3_android.gooding.my.MyAccountScreen
import com.dnd_9th_3_android.gooding.ui.theme.GoodingTheme


class MyGoodingFragment : Fragment() {
    private var _binding : FragmentMyGoodingBinding? = null
    private val binding get() = _binding!!
    lateinit var mainActivity: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMyGoodingBinding.inflate(layoutInflater,container,false)
        binding.myComposeView.apply {
            // follow fragment Lifecycle
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                GoodingTheme {
                    MyAccountScreen()
                }
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        fun newInstance() = MyGoodingFragment()
    }

}