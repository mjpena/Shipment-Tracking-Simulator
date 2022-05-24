package observer

import TrackingSimulator
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import subject.Shipment
import javax.swing.plaf.nimbus.State

class TrackerViewHelper(shipmentId: String): Observer {
    var shipmentId by mutableStateOf<String>(shipmentId)
    var shipmentStatus by mutableStateOf<String>("")
    var shipmentLocation by mutableStateOf<String>("")
    var shipmentDeliveryDate = mutableStateListOf<Long>()
    var shipmentNotes = mutableStateListOf<String>()
    var shipmentUpdateHistory = mutableStateListOf<String>()

    private fun getUpToDateShipment(): Shipment {
        return TrackingSimulator.findShipment(shipmentId)!!
    }

    fun trackShipment(){
        val shipment = getUpToDateShipment()
            ?: // todo: warning shipment dne
            return
        shipment.registerObserver(this)
        update()
    }

    fun stopTracking(){
        val shipment = getUpToDateShipment()
        shipment.removeObserver(this)
    }
    override fun update() {
        val shipment = getUpToDateShipment()
        shipmentStatus = shipment.status
        shipmentLocation = shipment.currentLocation
        shipmentDeliveryDate.add(shipment.expectedDeliveryDate)
        shipmentNotes = shipment.notes as SnapshotStateList<String>
        shipmentUpdateHistory = shipment.updateHistory.toString() as SnapshotStateList<String>
    }
}