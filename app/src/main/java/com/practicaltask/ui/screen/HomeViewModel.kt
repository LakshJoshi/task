package com.practicaltask.ui.screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.practicaltask.data.entity.APIResult
import com.practicaltask.data.entity.home.Home
import com.practicaltask.data.repository.HomeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class HomeViewModel(application: Application) : AndroidViewModel(application)  {
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Main
    private val scope: CoroutineScope = CoroutineScope(coroutineContext)
    private val homeRepo = HomeRepository(application)

    var home = Home()
    fun getHomeData(listener: (APIResult<Home>) -> Unit) {
        scope.launch {
            homeRepo.getHomeData { it->
                when (it) {
                    is APIResult.Success -> {
                        home = it.data
                        listener(it)
                    }
                    is APIResult.Failure -> {
                        listener(it)
                    }
                    APIResult.InProgress -> {
                        listener(APIResult.InProgress)
                    }
                }
            }
        }
    }
}