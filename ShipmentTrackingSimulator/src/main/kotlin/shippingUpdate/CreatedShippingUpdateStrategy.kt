package shippingUpdate

import TrackingSimulator
import subject.Shipment

class CreatedShippingUpdateStrategy(): ShippingUpdateStrategy {
    private fun createShipment(shippingUpdateId: String){
        var shipment: Shipment = Shipment(shippingUpdateId, "N/A")
        TrackingSimulator.addShipment(shipment)
    }

    override fun updateShipment(shippingInformation: String) {
        createShipment(getShippingUpdateId(shippingInformation))
        addShippingUpdateToShipment(shippingInformation)
    }
}