package com.techtribeservices.playgroundapp.samples.todo.viewModels

data class AppUiState(
    val todoItems: List<String> = emptyList(),
    var isLoading:Boolean = true

)
