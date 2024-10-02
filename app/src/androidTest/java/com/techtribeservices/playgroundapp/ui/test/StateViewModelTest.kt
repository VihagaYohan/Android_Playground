package com.techtribeservices.playgroundapp.ui.test

import com.techtribeservices.playgroundapp.samples.todo.viewModels.StateViewModel
import org.jetbrains.annotations.TestOnly
import org.junit.Test

class StateViewModelTest {
    private val viewModel = StateViewModel()

    @Test
    fun stateViewModel_changeLoaderState_isLoadingIsTrue() {
        var currentUIState = viewModel.uiState.value
        val currentLoadingState = viewModel.uiState.value.isLoading
    }
}