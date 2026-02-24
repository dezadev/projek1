package com.umkm.pos.ui.base

import android.os.Bundle
import androidx.activity.ComponentActivity

abstract class BaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    open fun setupObservers() = Unit
}
