package shippingUpdate

import TrackingServer
import subject.Shipment

class CreatedShippingUpdateStrategy(): ShippingUpdateStrategy {
    private fun createShipment(shippingInformation: String){
        val shippingInformationParser: ShippingInformationParser = ShippingInformationParser(shippingInformation)
        val shipment: Shipment = Shipment.getShipment(shippingInformationParser.getShippingUpdateId(), shippingInformationParser.getShippingUpdateShipmentType(), shippingInformationParser.getShippingUpdateTimestamp(), shippingInformationParser.getShippingUpdateExpectedDeliveryDate())
        TrackingServer.addShipment(shipment)
    }

    override fun updateShipment(shippingInformation: String) {
        createShipment(shippingInformation)
        addShippingUpdateToShipment(shippingInformation)
    }
}