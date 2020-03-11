package com.test.pumpit.ui.youdrive

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class YouDriveViewModel : ViewModel() {

    val text = ObservableField<String>()

    init {
        text.set("YouDrive map")
    }

}