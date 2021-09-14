package com.example.stackusers.view

import androidx.lifecycle.*
import com.example.stackusers.R
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


open class BaseViewModel : ViewModel() {

    /**
     * Allows ViewModels to execute coroutines safely
     */
    protected fun safeCall(dispatcher: CoroutineDispatcher? = null, method: suspend () -> Unit) {
        CoroutineScope(dispatcher ?: Dispatchers.Main).launch {
            try {
                method.invoke()
            } catch (e: Throwable) {
                e.stackTrace.forEach { println(it) }
                println(e.message)
                BaseActivity.ErrorNotification.postEvent(R.string.server_error)
            }
        }
    }

    /**
     * Class implemented for single one time events with any type of content
     */
    class Event<out T>(private val content: T) {

        private var hasBeenHandled: Boolean = false

        fun getContentIfNotHandled(): T? {
            return if (hasBeenHandled) {
                null
            } else {
                hasBeenHandled = true
                content
            }
        }
    }

    /**
     * Class implemented for single on time events, but nothings gets passed as data
     */
    class VoidEvent {

        var hasBeenHandled: Boolean = false

        fun hasBeenHandled(): Boolean {
            return if (hasBeenHandled) {
                true
            } else {
                hasBeenHandled = true
                false
            }
        }

    }

}

fun <T> MutableLiveData<out BaseViewModel.Event<T>>.postEvent(content: T) {
    value = BaseViewModel.Event(content)
}

fun <T> LiveData<out BaseViewModel.Event<T>>.observeEvent(
    owner: LifecycleOwner,
    onEventUnhandled: (T) -> Unit
) {
    observe(owner, Observer { it?.getContentIfNotHandled()?.let(onEventUnhandled) })
}

fun LiveData<out BaseViewModel.VoidEvent>.observeEvent(
    owner: LifecycleOwner,
    onEventUnhandled: () -> Unit
) {
    observe(owner, Observer { if (!it.hasBeenHandled()) onEventUnhandled() })
}