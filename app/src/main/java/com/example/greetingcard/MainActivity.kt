package com.example.greetingcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
// import com.example.todoapp.ui.theme.TodoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                ToDoAppScaffold()
            }
        }
    }
}

@Composable
fun ToDoAppScaffold() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        ToDoApp(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun ToDoApp(modifier: Modifier = Modifier) {
    var taskText by remember { mutableStateOf("") }
    var tasks by remember { mutableStateOf(listOf<String>()) }

    Column(modifier = modifier.padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            BasicTextField(
                value = taskText,
                onValueChange = { taskText = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )
            Button(onClick = {
                if (taskText.isNotEmpty()) {
                    tasks = tasks + taskText
                    taskText = ""
                }
            }) {
                Text(text = "Add Task")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column {
            tasks.forEach { task ->
                Text(text = task, modifier = Modifier.padding(4.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoAppPreview() {
    MaterialTheme {
        ToDoAppScaffold()
    }
}
