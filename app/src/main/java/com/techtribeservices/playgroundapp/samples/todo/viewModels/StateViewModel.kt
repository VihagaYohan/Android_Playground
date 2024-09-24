package com.techtribeservices.playgroundapp.samples.todo.viewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class StateViewModel: ViewModel() {
    // ui state
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    // show/hide loader
    fun changeLoaderState() {
        _uiState.value = AppUiState(
            isLoading = !_uiState.value.isLoading
        )
    }

    // add item to list
    fun addItemToList(item:String) {
        _uiState.value = AppUiState(
            isLoading = !_uiState.value.isLoading,
            todoItems = _uiState.value.todoItems + item
        )
    }

}