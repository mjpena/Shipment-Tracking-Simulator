package shippingUpdate

import TrackingSimulator
import subject.Shipment

class CreatedShippingUpdateStrategy(): ShippingUpdateStrategy {
    private fun createShipment(shippingUpdateId: String){
        var shipment: Shipment = Shipment(shippingUpdateId, "N/A")
        TrackingSimulator.addShipment(shipment)
    }

    override fun updateShipment(shippingInformation: String) {
        val shippingInformationParser: ShippingInformationParser = ShippingInformationParser(shippingInformation)
        createShipment(shippingInformationParser.getShippingUpdateId())
        addShippingUpdateToShipment(shippingInformation)
    }
}