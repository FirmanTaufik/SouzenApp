package com.efte.souzenapp.presentation.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.efte.souzenapp.core.BaseVM
import com.efte.souzenapp.core.UIState
import com.efte.souzenapp.repo.PageImple
import kotlinx.coroutines.launch

class HomeVM() : BaseVM() {

    var homeState by mutableStateOf<UIState>(UIState.OnIdle)
    fun getHome() = viewModelScope
        .launch {
            homeState= UIState.OnLoading
            try {
                val result = pageSource.getHome()
                homeState   = UIState.OnSuccess(result)
            }catch (e : Exception){
                homeState = UIState.OnError(e.message)
            }
        }
}