package com.test.pumpit.ui.issues

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class IssuesViewModel : ViewModel() {

    val text = ObservableField<String>()

    init {
        text.set("git issues")
    }

}