package com.choi.beatbox

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.choi.beatbox.sounds.Sound

class SoundViewModel : BaseObservable(){

    var sound : Sound? = null
        set(sound) {
            field=sound
            notifyChange()
        }

    @get:Bindable
    val title: String?
        get() = sound?.name
 }