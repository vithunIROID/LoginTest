package com.iroid.logintest.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.iroid.logintest.R
import com.iroid.logintest.utils.Status
import com.iroid.logintest.databinding.FragmentLoginBinding
import com.iroid.logintest.ui.login.viewmodel.LoginViewModel
import com.iroid.logintest.utils.CustomProgressDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment() : Fragment() {

    private val loginViewModel : LoginViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding
    //private val loginViewModel by viewModels<LoginViewModel>()

    private var customProgressDialog: CustomProgressDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.viewModel = loginViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        customProgressDialog = CustomProgressDialog(activity)

        setupObserver()

    }

    //Observers for any change occurring for View Model
    private fun setupObserver() {

        loginViewModel.emptyUsername?.observe(viewLifecycleOwner, Observer { message ->
            if(message!=null)
                binding.tilUsername.error = activity?.getString(message)
            else
                binding.tilUsername.isErrorEnabled = false
        })


        loginViewModel.emptyPassword?.observe(viewLifecycleOwner, Observer { message ->
            if(message!=null)
                binding.tilPassword.error = activity?.getString(message)
            else
                binding.tilPassword.isErrorEnabled = false
        })


        //Do the need after the api call
        loginViewModel.users.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    customProgressDialog?.hide()
                    Toast.makeText(activity, it.data?.errorMessage, Toast.LENGTH_LONG).show()
                    login()
                }
                Status.LOADING -> {
                    customProgressDialog?.show()
                }
                Status.ERROR -> {
                    customProgressDialog?.hide()
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    //navigate to Login Success Fragment
    private fun login() {
        findNavController().navigate(R.id.action_LoginFragment_to_LoginSuccessFragment)
    }


}