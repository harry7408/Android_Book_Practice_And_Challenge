package com.example.jpub_practice

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class CheatViewModel(application: Application) : AndroidViewModel(application) {
    var buttonFlag=false
    var answer=false
}