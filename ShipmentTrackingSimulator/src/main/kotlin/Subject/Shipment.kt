package subject

import observer.TrackerViewHelper
import shippingUpdate.ShippingUpdate
abstract class Shipment(id: String): Subject {
    val observers: MutableList<TrackerViewHelper> = mutableListOf()
    val id: String = id
    var status: String = "N/A"
        set(value) {
            field = value
            notifyObservers()
        }
    var currentLocation: String = "unknown"
        set(value) {
            field = value
            notifyObservers()
        }
    var expectedDeliveryDate: Long = 0
        set(value) {
            if (value < 0){
                throw Exception("Expected delivery date cannot be a negative time.")
            }
            field = value
            notifyObservers()
        }
    val notes: MutableList<String> = mutableListOf()
    val updateHistory: MutableList<ShippingUpdate> = mutableListOf()

    fun addNote(note: String){
        notes.add(note)
        notifyObservers()
    }

    fun addUpdate(shippingUpdate: ShippingUpdate){
        updateHistory.add(shippingUpdate)
        this.status = shippingUpdate.newStatus
        notifyObservers()
    }

    companion object {
        fun getShipment(shipmentType: String, shippingId: String): Shipment {
            if (shipmentType == "standard") return StandardShipment(shippingId)
            if (shipmentType == "express") return ExpressShipment(shippingId)
            if (shipmentType == "overnight") return OvernightShipment(shippingId)
            if (shipmentType == "bulk") return BulkShipment(shippingId)
            throw Exception("Invalid shipment type: $shipmentType")
        }
    }

    override fun notifyObservers(){
        for (observer in observers){
            observer.update()
        }
    }

    override fun registerObserver(trackerViewHelper: TrackerViewHelper) {
        if (observers.contains(trackerViewHelper)){
            return
        }
        observers.add(trackerViewHelper)
    }

    override fun removeObserver(trackerViewHelper: TrackerViewHelper){
        if (!observers.contains(trackerViewHelper)){
            return
        }
        observers.remove(trackerViewHelper)
    }
}