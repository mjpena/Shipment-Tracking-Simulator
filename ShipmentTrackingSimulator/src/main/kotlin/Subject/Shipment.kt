package subject

import observer.TrackerViewHelper
import shippingUpdateStrategies.ShippingUpdate

class Shipment(id: String, status: String): Subject {
    private val observers: MutableList<TrackerViewHelper> = mutableListOf()

    val id: String = id
    var status: String = status
    var currentLocation: String = "unknown"
    var expectedDeliveryDate: Long = 0
    var notes: MutableList<String> = mutableListOf()
        private set
    var updateHistory: MutableList<ShippingUpdate> = mutableListOf()
        private set

    fun addNote(note: String){
        notes.add(note)
    }

    fun addUpdate(shippingUpdate: ShippingUpdate){
        updateHistory.add(shippingUpdate)
    }

    override fun notifyObserver(){
        for (observer in observers){
            observer.update()
        }
    }

    override fun registerObserver(trackerViewHelper: TrackerViewHelper) {
        observers.add(trackerViewHelper)
    }

    override fun removeObserver(trackerViewHelper: TrackerViewHelper){
        observers.remove(trackerViewHelper)
    }
}