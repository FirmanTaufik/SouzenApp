package com.efte.souzenapp.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.efte.souzenapp.Greeting
import com.efte.souzenapp.core.UIState

@Composable
fun HomeScreen() {

    val homeViewModel = HomeVM()
    LaunchedEffect(key1 = true) {
        homeViewModel.getHome()
    }

    when (val state = homeViewModel.homeState) {
        is UIState.OnLoading -> Greeting("LOADING")
        is UIState.OnError -> {
            Greeting(state.message ?: "")
        }

        is UIState.OnSuccess<*> -> {
            val data = state.data
            Greeting(data.toString())
        }

        else -> {

            Greeting("IDLE")
        }
    }

}