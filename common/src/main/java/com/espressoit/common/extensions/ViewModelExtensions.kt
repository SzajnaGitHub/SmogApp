package com.espressoit.common.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.espressoit.common.exception.ExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun ViewModel.launch(exceptionHandler: ExceptionHandler, block: suspend () -> Unit) {
    viewModelScope.launch(Dispatchers.Default + exceptionHandler) {
        block.invoke()
    }
}
