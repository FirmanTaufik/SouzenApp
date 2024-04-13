package com.efte.souzenapp.core

sealed class UIState {

    object OnIdle : UIState()

    object OnLoading : UIState()

    data class OnSuccess<T>(val data: T) : UIState()

    class OnError(val message: String ?= "Terjadi Kesalahan Coba Lagi") : UIState()

}
