package com.othadd.chopmore

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.othadd.chopmore.databinding.FragmentFlexBinding
import com.othadd.chopmore.databinding.FragmentLoginSignUpBinding
import kotlin.math.sign

class LoginSignUpFragment : Fragment() {

    private val sharedViewModel: ChopmoreViewModel by activityViewModels {
        ChopmoreViewModelFactory()
    }

    private lateinit var binding: FragmentLoginSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginSignUpBinding.inflate(inflater, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            loginSignUpFragment = this@LoginSignUpFragment
            viewmodel = sharedViewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.inLoginMode.observe(viewLifecycleOwner){
            if (it){
                updateUIForLogin()
            }
            else{
                updateUIForSignup()
            }
        }

        binding.constraintLayout.transitionToEnd()
    }

    private fun hideDialog(dialog: View){
        val movePropertyValueHolder = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 1200f)
        val transparencyValueHolder = PropertyValuesHolder.ofFloat(View.ALPHA, 0.0f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(dialog, movePropertyValueHolder, transparencyValueHolder)
        animator.start()
    }

    fun updateUIForLogin(){
        binding.apply {
            loginTextView.setBackgroundResource(R.drawable.bg_button_brown)
            loginTextView.setTextColor(resources.getColor(R.color.white, context?.theme))
            signupTextView.setBackgroundResource(R.drawable.bg_button_white)
            signupTextView.setTextColor(resources.getColor(R.color.black, context?.theme))
            forgotPasswordTextView.visibility = View.GONE
            confirmPasswordEditText.visibility = View.GONE
            loginSignupButtonTextView.text = "Log in"
        }
    }

    fun updateUIForSignup(){
        binding.apply {
            loginTextView.setBackgroundResource(R.drawable.bg_button_white)
            loginTextView.setTextColor(resources.getColor(R.color.black, context?.theme))
            signupTextView.setBackgroundResource(R.drawable.bg_button_brown)
            signupTextView.setTextColor(resources.getColor(R.color.white, context?.theme))
            forgotPasswordTextView.visibility = View.VISIBLE
            confirmPasswordEditText.visibility = View.VISIBLE
            loginSignupButtonTextView.text = "Sign Up"
        }
    }

    fun move(){
        binding.constraintLayout.transitionToStart()

        Handler().postDelayed({
            findNavController().navigate(LoginSignUpFragmentDirections.actionLoginSignUpFragmentToLandingFragment())
        }, 500)
    }

    fun enterLoginMode(){
        sharedViewModel.enterLoginMode(true)
    }

    fun enterSignUpMode(){
        sharedViewModel.enterLoginMode(false)
    }

}