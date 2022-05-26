// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import observer.TrackerViewHelper
import javax.swing.Scrollable

@Composable
fun trackerView(trackerViewHelper: TrackerViewHelper, trackerViewHelpers: SnapshotStateList<TrackerViewHelper>){
    Column(modifier = Modifier.fillMaxWidth().padding(10.dp).border(border = BorderStroke(width = 1.dp, color = Color.Black))){
        Row(modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween){
            var text = if (TrackingSimulator.findShipment(trackerViewHelper.shipmentId) == null){
                "Shipment with ID - ${trackerViewHelper.shipmentId} not found"
            } else{
                "Tracking Shipment: ${trackerViewHelper.shipmentId}"
            }
            Text(text, fontSize = 30.sp)
            IconButton(onClick = {
                trackerViewHelper.stopTracking()
                trackerViewHelpers.remove(trackerViewHelper)
            }){
                Icon(Icons.Default.Close, "Stop Tracking Shipment")
            }
        }
        Column(modifier = Modifier.padding(20.dp)){
            if(TrackingSimulator.findShipment(trackerViewHelper.shipmentId) != null){
                Row{ Text("Status: ${trackerViewHelper.shipmentStatus}")}
                Row{ Text("Location: ${trackerViewHelper.shipmentLocation}")}
                Row{ Text("Expected Delivery Date: ${trackerViewHelper.shipmentDeliveryDate.last()}")}
                Row{ Text("")}
                Row{ Text("Status Updates:")}
                for (update in trackerViewHelper.shipmentUpdateHistory) {
                    Row{ Text(update)}
                }
                Row{ Text("")}
                Row{ Text("Notes:")}
                for (note in trackerViewHelper.shipmentNotes) {
                    Row{ Text(note)}
                }
            }
        }
    }
}

@Composable
@Preview
fun App() {
    val trackerViewHelpers = remember { mutableStateListOf<TrackerViewHelper>() }
    var userInput by mutableStateOf("")

    MaterialTheme {
        Column() {
            Row(modifier = Modifier.fillMaxWidth().padding(10.dp)){
                TextField(userInput, { userInput = it })
                Button(onClick = {
                    trackerViewHelpers.add(TrackerViewHelper(userInput))
                    userInput = ""
                }) {
                    Text("Track Shipment")
                }
            }
            LazyColumn {
                items(trackerViewHelpers){trackerViewHelper ->
                    trackerView(trackerViewHelper, trackerViewHelpers)
                }
            }
        }
    }
}

fun main() = runBlocking {
    launch{
        TrackingSimulator.runSimulation("C:\\Users\\melan\\Documents\\School\\Summer 2022\\CS 5700\\assignment_2\\test.txt")
    }

    application {
        Window(onCloseRequest = ::exitApplication) {
            App()
        }
    }
}



