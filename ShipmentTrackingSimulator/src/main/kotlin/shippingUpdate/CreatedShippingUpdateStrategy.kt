package shippingUpdate

import TrackingSimulator
import subject.Shipment

class CreatedShippingUpdateStrategy(shippingInformation: String): ShippingUpdateStrategy(shippingInformation) {
    override fun updateShipment() {
        val shipment: Shipment = Shipment(shippingUpdateId, shippingUpdateStatus)
        shipment.addUpdate(createShippingUpdate("nonexistent"))
        TrackingSimulator.addShipment(shipment)
    }
}