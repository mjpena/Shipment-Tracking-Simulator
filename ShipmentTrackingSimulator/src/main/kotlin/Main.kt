// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import observer.TrackerViewHelper
import java.lang.reflect.Modifier

@Composable
fun TrackerView(trackerViewHelper: TrackerViewHelper, coroutineScope: CoroutineScope){

}

@Composable
@Preview
fun App() {
    val trackerViewHelpers = remember { mutableStateListOf<TrackerViewHelper>() }
    var userInput by mutableStateOf("")

    MaterialTheme {
        val coroutineScope = rememberCoroutineScope()

        Column {
            Row{
                TextField(userInput, { userInput = it })
                Button(onClick = {
                    trackerViewHelpers.add(TrackerViewHelper(userInput))
                    //trackerViewHelpers.last().trackShipment()
                    userInput = ""
                }) {
                    Text("Track Shipment")
                }
            }
            LazyColumn {
                items(trackerViewHelpers){trackerViewHelper ->
                    TrackerView(trackerViewHelper, coroutineScope)
                }
            }
        }
    }
}

fun main() = runBlocking {
//    launch{
//        TrackingSimulator.runSimulation()
//    }

    application {
        Window(onCloseRequest = ::exitApplication) {
            App()
        }
    }
}



