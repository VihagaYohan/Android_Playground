package com.techtribeservices.playgroundapp.samples.todo

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.techtribeservices.playgroundapp.samples.todo.viewModels.StateViewModel
import com.techtribeservices.playgroundapp.ui.theme.PlaygroundAppTheme

@Composable
fun TodoHome(
    modifier: Modifier = Modifier,
    appViewModel: StateViewModel = viewModel()
) {
    val appState by appViewModel.uiState.collectAsState()

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 10.dp)
        ) {
            Header(onSubmit = { it ->
                appViewModel.addItemToList(it)
                println(appState.todoItems)
            })

            Spacer(
                modifier =  Modifier
                    .height(10.dp)
            )

            TodoList(appState.todoItems)

            /*if(appState.isLoading) {
                Loader()
            }*/

            /*when(appState.isLoading) {
                true -> {
                    // showAlert()
                }

                false -> {
                    showAlert(
                        onConfirmed = {
                            appViewModel.changeLoaderState()
                        }
                    )
                }
            }*/
        }
    }
}

@Composable
fun Header(onSubmit:(String) -> Unit) {
    var currentItem by remember {
        mutableStateOf("")
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        OutlinedTextField(
            value = currentItem,
            onValueChange = { it ->
                currentItem = it
            },
            label = {
                Text(text = "Enter Todo Item")
            },
            modifier = Modifier
                .weight(1f)
        )

        Button(
            modifier = Modifier
                .wrapContentWidth(),
            onClick = {
                if(currentItem.isNotEmpty()) {
                    onSubmit(currentItem)
                    currentItem = ""
                } else {
                    println("item is empty")
                }
            }
        ) {
            Text(text = "Add")
        }
    }
}

@Composable
fun Loader() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(40.dp),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}

@Composable
fun AlertMessage(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}

// show alert message
@Composable
fun showAlert(
    onConfirmed:(Boolean) -> Unit
) {
    AlertMessage(
        onDismissRequest = {false},
        onConfirmation = {
            println("confirmed")
            onConfirmed(false)
        },
        dialogTitle = "Message",
        dialogText = "New item has been added",
        icon = Icons.Default.Info
    )
}

// items list
@Composable
fun TodoList(list:List<String>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(list) {item ->
            TodoItem(item)
        }
    }
}

@Composable
fun TodoItem(item:String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(
                horizontal = 10.dp,
                vertical = 10.dp
            ),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item,
            style = TextStyle(
                fontSize = 15.sp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TodoHomePreview() {
    PlaygroundAppTheme {
        TodoHome()
    }
}