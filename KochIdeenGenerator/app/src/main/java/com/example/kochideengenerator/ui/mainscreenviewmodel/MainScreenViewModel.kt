package com.example.kochideengenerator.ui.mainscreenviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.kochideengenerator.data.datasource.IdeasDatabase
import com.example.kochideengenerator.data.repository.IdeasRepository
import com.example.kochideengenerator.domain.model.Idea
import com.example.kochideengenerator.ui.IdeasEvent
import com.example.kochideengenerator.ui.MainScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val ideasRepository: IdeasRepository
    private var parentJob = Job()
    private val couroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(couroutineContext)

    private val _state = MutableStateFlow(MainScreenState())

    val state: StateFlow<MainScreenState> = _state.asStateFlow()

    init {
        val database = IdeasDatabase.getDatabase(application,scope)
        val ideasDao = database.ideasDao
        ideasRepository = IdeasRepository(ideasDao)
    }

    fun getListOfIdeas(): List<Idea> {
        return ideasRepository.getListOfIdeas()
    }

    fun onEvent(event: IdeasEvent) {
        when (event) {
            is IdeasEvent.SwipeLeft -> {

            }

            is IdeasEvent.SwipeRight -> {

            }

            is IdeasEvent.SwipeUp -> {

            }


            is IdeasEvent.SwipeDown -> {

            }

            is IdeasEvent.DoubleClick -> {
                _state.update { currentScreenState ->
                    currentScreenState.copy(
                        displayedIdea = Idea("cac","","",5)
                    )
                }

            }

            else -> {}
        }
    }


}