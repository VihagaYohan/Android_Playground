package com.techtribeservices.playgroundapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.techtribeservices.playgroundapp.ui.theme.PlaygroundAppTheme
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.techtribeservices.playgroundapp.samples.todo.TodoHome
import com.techtribeservices.playgroundapp.viewModels.StateTestViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaygroundAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   /* TitleCard(
                        modifier = Modifier.padding(innerPadding)
                    )*/
                    TodoHome(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TitleCard(
    modifier: Modifier = Modifier,
    viewModel: StateTestViewModel = StateTestViewModel()
) {
    val name by viewModel.name.observeAsState("")

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyText(name)
        MyTextField(name, onNameChange = {
            viewModel.onNameUpdate(it)
        })
    }
}

@Composable
fun MyText(name:String) {
    Text(
        text = "Hello $name",
        style = TextStyle(
            fontSize = 30.sp
        )
    )
}

@Composable
fun MyTextField(
    name:String,
    onNameChange:(String) -> Unit
) {
    OutlinedTextField(
        value = "",
        onValueChange = {
            onNameChange(it)
        },
        label = {
            Text(
                text = "Enter name"
            )
        }
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PlaygroundAppTheme {
        TitleCard()
    }
}