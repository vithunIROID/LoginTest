package com.iroid.logintest.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.iroid.logintest.R
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject
import javax.inject.Singleton


class CustomProgressDialog (context: Context?) : Dialog(context!!) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_progress)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }

}