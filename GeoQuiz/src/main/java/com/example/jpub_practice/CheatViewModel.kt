package com.example.jpub_practice

import android.app.Activity
import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel

class CheatViewModel(application: Application) : AndroidViewModel(application) {
    var buttonFlag=false
    var answer=false
}