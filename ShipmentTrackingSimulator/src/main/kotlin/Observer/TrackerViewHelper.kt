package observer

import TrackingSimulator
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import subject.ShipmentFactory

class TrackerViewHelper(shipmentId: String): Observer {
    var shipmentId by mutableStateOf<String>(shipmentId)
        private set
    var shipmentStatus by mutableStateOf<String>("")
        private set
    var shipmentLocation by mutableStateOf<String>("")
        private set
    var shipmentDeliveryDate = mutableStateListOf<Long>()
        private set
    var shipmentNotes = mutableStateListOf<String>()
        private set
    var shipmentUpdateHistory = mutableStateListOf<String>()
        private set
    init {
        trackShipment()
    }
    fun trackShipment(){
        val shipmentFactory: ShipmentFactory = TrackingSimulator.findShipment(shipmentId) ?: return
        shipmentFactory.registerObserver(this)
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
        for (note in shipment.notes){
            if (!shipmentNotes.contains(note)){
                shipmentNotes.add(note)
            }
        }
        for (update in shipment.updateHistory){
            if (!shipmentUpdateHistory.contains(update.toString())){
                shipmentUpdateHistory.add(update.toString())
            }
        }
    }
}