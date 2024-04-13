package com.efte.souzenapp.core

import androidx.lifecycle.ViewModel
import com.efte.souzenapp.repo.PageImple

open class BaseVM(val pageSource : PageImple = PageImple()) : ViewModel() {

}