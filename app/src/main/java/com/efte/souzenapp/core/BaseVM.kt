package com.efte.souzenapp.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.efte.souzenapp.repo.PageImple
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class BaseVM(val pageSource: PageImple = PageImple()) : ViewModel() {

    fun <T> stateDataDelegate(defaultValue: T): ReadWriteProperty<Any, StateFlow<T>> =
        object : ReadWriteProperty<Any, StateFlow<T>> {
            private val data: MutableStateFlow<T> = MutableStateFlow(defaultValue)

            override fun getValue(thisRef: Any, property: KProperty<*>): StateFlow<T> = data

            override fun setValue(thisRef: Any, property: KProperty<*>, value: StateFlow<T>) {
                data.value = value.value
            }
        }

    fun UIState.asNewState() = MutableStateFlow(this)

}