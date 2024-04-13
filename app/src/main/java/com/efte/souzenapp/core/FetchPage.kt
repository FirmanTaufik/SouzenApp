package com.efte.souzenapp.core

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import kotlin.coroutines.CoroutineContext

class FetchPage(val baseUrl: String? = "https://anime-indo.lol/") {
    private val TAG = "FetchPageTAG"
    suspend fun get(pageUrl: String? = ""): String = withContext(Dispatchers.IO) {
   //     Log.d(TAG, "fetchData: $pageUrl")
        var result = ""
        try {
            val jsoup = Jsoup.connect(baseUrl + pageUrl)
                .timeout(30000000)
                .get()
            val doc = jsoup.getElementById("content-wrap")
            result= doc.toString()
            Log.d(TAG, "fetchData: ${result}")
        } catch (e: Exception) {
            Log.d(TAG, "fetchData: ${e.message}")
            return@withContext result
        } catch (r: RuntimeException) {
            Log.d(TAG, "fetchData: ${r.message}")
            return@withContext result
        }
        return@withContext result
    }


}