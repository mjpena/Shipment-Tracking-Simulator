package shippingUpdate

import TrackingServer
import subject.Shipment

class CreatedShippingUpdateStrategy(): ShippingUpdateStrategy {
    private fun createShipment(shippingInformation: String){
        val shippingInformationParser: ShippingInformationParser = ShippingInformationParser(shippingInformation)
        val shipment: Shipment = Shipment.getShipment(shippingInformationParser.getShippingUpdateShipmentType(), shippingInformationParser.getShippingUpdateId())
        TrackingServer.addShipment(shipment)
    }

    override fun updateShipment(shippingInformation: String) {
        createShipment(shippingInformation)
        addShippingUpdateToShipment(shippingInformation)
    }
}