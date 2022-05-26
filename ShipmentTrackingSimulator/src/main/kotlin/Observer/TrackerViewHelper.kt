package observer

import TrackingSimulator
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import subject.Shipment

class TrackerViewHelper(shipmentId: String): Observer {
    var shipmentId by mutableStateOf<String>(shipmentId)
    var shipmentStatus by mutableStateOf<String>("")
    var shipmentLocation by mutableStateOf<String>("")
    var shipmentDeliveryDate = mutableStateListOf<Long>()
    var shipmentNotes = mutableStateListOf<String>()
    var shipmentUpdateHistory = mutableStateListOf<String>()

    init {
        trackShipment()
    }
    fun trackShipment(){
        val shipment: Shipment = TrackingSimulator.findShipment(shipmentId) ?: return
        shipment.registerObserver(this)
        update()
    }

    fun stopTracking() {
        TrackingSimulator.findShipment(shipmentId)?.removeObserver(this)
    }
    override fun update() {
        val shipment = TrackingSimulator.findShipment(shipmentId)!!
        shipmentStatus = shipment.status
        shipmentLocation = shipment.currentLocation
        shipmentDeliveryDate.add(shipment.expectedDeliveryDate)
        if (shipment.notes.size >0 && !shipmentNotes.contains(shipment.notes.last())){
            shipmentNotes.add(shipment.notes.last())
        }
        shipmentUpdateHistory.add(shipment.updateHistory.last().toString())
    }
}