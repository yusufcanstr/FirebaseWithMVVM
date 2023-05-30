package com.yusufcansenturk.firebasewithmvvm.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.yusufcansenturk.firebasewithmvvm.R
import com.yusufcansenturk.firebasewithmvvm.databinding.FragmentForgotPasswordBinding
import com.yusufcansenturk.firebasewithmvvm.util.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgotPasswordBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForgotPasswordBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        binding.forgotPassBtn.setOnClickListener {
            if (validation()){
                viewModel.forgotPassword(binding.emailEt.text.toString())
            }
        }
    }

    private fun observer(){
        viewModel.forgotPassword.observe(viewLifecycleOwner) { state ->
            when(state){
                is UiState.Loading -> {
                    binding.forgotPassBtn.text = ""
                    binding.forgotPassProgress.show()
                }
                is UiState.Failure -> {
                    binding.forgotPassBtn.text = "Send"
                    binding.forgotPassProgress.hide()
                    toast(state.error)
                }
                is UiState.Success -> {
                    binding.forgotPassBtn.text = "Send"
                    binding.forgotPassProgress.hide()
                    toast(state.data)
                }
            }
        }
    }

    private fun validation(): Boolean {
        var isValid = true

        if (binding.emailEt.text.isNullOrEmpty()){
            isValid = false
            toast(getString(R.string.enter_email))
        }else{
            if (!binding.emailEt.text.toString().isValidEmail()){
                isValid = false
                toast(getString(R.string.invalid_email))
            }
        }

        return isValid
    }


}