package com.iroid.logintest.ui.login.viewmodel

import android.text.TextUtils
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import com.iroid.logintest.R
import com.iroid.logintest.database.entity.UserEntity
import com.iroid.logintest.model.LoginResponse
import com.iroid.logintest.model.UserData
import com.iroid.logintest.repository.UserRepository
import com.iroid.logintest.utils.Resource
import com.iroid.logintest.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
        private val userRepository: UserRepository,
        private val utils: Utils) : ViewModel() {


    var btnSelected: ObservableBoolean? = null
    var username: MutableLiveData<String>? = null
    var password: MutableLiveData<String>? = null
    var userLogin: MutableLiveData<LoginResponse>? = null

    var emptyUsername: MutableLiveData<Int>? = null
    var emptyPassword: MutableLiveData<Int>? = null

    private val _users = MutableLiveData<Resource<LoginResponse>>()

    val users: LiveData<Resource<LoginResponse>>
        get() = _users


    init {
        btnSelected = ObservableBoolean(true)
        username = MutableLiveData()
        password = MutableLiveData()
        userLogin = MutableLiveData<LoginResponse>()
        emptyUsername = MutableLiveData()
        emptyPassword = MutableLiveData()
    }


    //login API call, if the call is success insert the user details to the DB
    fun login() {
        if(validate()) {
            viewModelScope.launch {
                _users.postValue(Resource.loading(null))
                if (utils.isNetworkConnected()) {
                    //API call
                    userRepository.userLogin(username?.value!!, password?.value!!).let {
                        if (it.isSuccessful) {
                            val loginResponse  = it.body()

                            insertToDatabase(loginResponse!!.user)
                            // update the observer for success
                            _users.postValue(Resource.success(loginResponse))
                        } else {
                            //update the observer for failure
                            _users.postValue(Resource.error(it.errorBody().toString(), null))
                        }
                    }
                } else {
                    //update the observer for failure
                    _users.postValue(Resource.error("No internet connection", null))
                }
            }
        }

    }

    // insert User into Room DB
    private fun insertToDatabase(userData: UserData) {
        val userEntity =  UserEntity(userData.userId, userData.userName)
        CoroutineScope(Dispatchers.IO).launch {
            userRepository.insertUser(userEntity)
        }
    }

    //show/hide username error based on the input
    fun onUsernameTextChanged(text: CharSequence) {
        if(TextUtils.isEmpty(text.toString())) {
            emptyUsername?.value =  R.string.username_required
        }else{
            emptyUsername?.value =  null
        }
    }


    //show/hide password error based on the input
    fun onPasswordTextChanged(text: CharSequence) {
        if(TextUtils.isEmpty(text.toString())) {
            emptyPassword?.value =  R.string.password_required
        }else{
            emptyPassword?.value =  null
        }
    }


    //validate field on button click
    private fun validate(): Boolean {

        if(TextUtils.isEmpty(username?.value)) {
            emptyUsername?.value = R.string.username_required
            return false
        }
        else if (!utils.isUsernameValid(username?.value.toString())) {
            emptyUsername?.value = R.string.invalid_username
            return false
        }
        else if (TextUtils.isEmpty(password?.value)) {
            emptyPassword?.value = R.string.password_required
            return false
        }
        else if (!utils.isPasswordValid(password?.value.toString())) {
            emptyPassword?.value = R.string.invalid_password
            return false
        }
        else {
            return true
        }
    }

}