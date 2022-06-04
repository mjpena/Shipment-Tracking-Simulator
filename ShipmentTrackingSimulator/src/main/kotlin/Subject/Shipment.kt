package subject

import observer.TrackerViewHelper
import shippingUpdate.ShippingUpdate
abstract class Shipment(val id: String, shipmentType: String): Subject {
    val observers: MutableList<TrackerViewHelper> = mutableListOf()
    var shipmentType: String = shipmentType
        protected set
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
    abstract var expectedDeliveryDate: Long
    val notes: MutableList<String> = mutableListOf()
    val updateHistory: MutableList<ShippingUpdate> = mutableListOf()

    protected fun throwErrorIfNegative(newExpectedDeliveryDate: Long){
        if (newExpectedDeliveryDate < 0) {
            throw Exception("Expected delivery date cannot be a negative time.")
        }
    }
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
        fun getShipment(shipmentId: String, shipmentType: String, updateTimestamp: Long, expectedDeliveryDate: Long): Shipment {
            if (shipmentType == "standard") return StandardShipment(shipmentId, shipmentType, expectedDeliveryDate)
            if (shipmentType == "express") return ExpressShipment(shipmentId, shipmentType, updateTimestamp, expectedDeliveryDate)
            if (shipmentType == "overnight") return OvernightShipment(shipmentId, shipmentType, updateTimestamp, expectedDeliveryDate)
            if (shipmentType == "bulk") return BulkShipment(shipmentId, shipmentType, updateTimestamp, expectedDeliveryDate)
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