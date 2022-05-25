package subject

import observer.TrackerViewHelper
import shippingUpdate.ShippingUpdate
class Shipment(val id: String, var status: String): Subject {
    val observers: MutableList<TrackerViewHelper> = mutableListOf()
    var currentLocation: String = "unknown"
    var expectedDeliveryDate: Long = 0
        set(value) {
            if (value < 0){
                throw Exception("Expected delivery date cannot be a negative time.")
            }
            field = value
        }
    val notes: MutableList<String> = mutableListOf()
    val updateHistory: MutableList<ShippingUpdate> = mutableListOf()
    fun addNote(note: String){
        notes.add(note)
    }
    fun addUpdate(shippingUpdate: ShippingUpdate){
        updateHistory.add(shippingUpdate)
        this.status = shippingUpdate.newStatus
        notifyObservers()
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