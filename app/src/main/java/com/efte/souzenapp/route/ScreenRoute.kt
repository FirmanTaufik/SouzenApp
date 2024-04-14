package com.efte.souzenapp.route

sealed class ScreenRoute(val route :String){
    object DetailAnime:ScreenRoute("DetailAnime")
}
