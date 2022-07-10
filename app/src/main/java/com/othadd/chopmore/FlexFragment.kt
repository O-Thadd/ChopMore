package com.othadd.chopmore

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.othadd.chopmore.databinding.FragmentFlexBinding

class FlexFragment : Fragment() {

    private lateinit var binding: FragmentFlexBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFlexBinding.inflate(inflater, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            flexFragment = this@FlexFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed({
            findNavController().navigate(FlexFragmentDirections.actionFlexFragmentToLoginSignUpFragment())
        }, 500)
    }

    fun move(){
        findNavController().navigate(FlexFragmentDirections.actionFlexFragmentToLoginSignUpFragment())
    }
}