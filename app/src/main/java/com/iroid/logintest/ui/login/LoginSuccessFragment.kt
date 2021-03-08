package com.iroid.logintest.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.iroid.logintest.R
import com.iroid.logintest.databinding.FragmentLoginSuccessBinding
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class LoginSuccessFragment : Fragment() {

    private lateinit var binding: FragmentLoginSuccessBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_success, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //button click to navigate to Login Fragment
        binding.btOk.setOnClickListener {
            findNavController().navigate(R.id.action_LoginSuccessFragment_to_LoginFragment)
        }
    }
}