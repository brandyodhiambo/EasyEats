package com.odhiambodevelopers.easyeats.utils

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("CommitPrefEdits")
fun Context.onBoardingFinished(){
    val sharedPreferences = this.getSharedPreferences("shared_pref",Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putBoolean("finished",true).apply()
}

fun Context.checkIfOnBoardingFinished():Boolean{
   return this.getSharedPreferences("shared_pref",Context.MODE_PRIVATE).getBoolean("finished",false)
}