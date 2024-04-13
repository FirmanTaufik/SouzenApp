package com.efte.souzenapp.services

import com.efte.souzenapp.core.FetchPage

interface PageServices {

    suspend fun getHome() =  FetchPage()
        .get()


}