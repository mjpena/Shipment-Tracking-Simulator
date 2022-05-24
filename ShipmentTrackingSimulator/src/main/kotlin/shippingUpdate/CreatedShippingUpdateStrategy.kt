package shippingUpdate

import TrackingSimulator
import subject.Shipment

class CreatedShippingUpdateStrategy(): ShippingUpdateStrategy {
    private fun createShipment(shippingUpdateId: String){
        val shipment: Shipment = Shipment(shippingUpdateId, "nonexistent")
        TrackingSimulator.addShipment(shipment)
    }

    override fun updateShipment(shippingInformation: String) {
        createShipment(getShippingUpdateId(shippingInformation))
        getShipmentWithAddedShippingUpdate(shippingInformation)
    }
}