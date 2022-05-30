package shippingUpdate

import TrackingSimulator
import subject.ShipmentFactory

class CreatedShippingUpdateStrategy(): ShippingUpdateStrategy {
    private fun createShipment(shippingInformation: String){
        val shippingInformationParser: ShippingInformationParser = ShippingInformationParser(shippingInformation)
        val shipmentFactory: ShipmentFactory = ShipmentFactory.getShipment(shippingInformationParser.getShippingUpdateShipmentType(), shippingInformationParser.getShippingUpdateId())
        TrackingSimulator.addShipment(shipmentFactory)
    }

    override fun updateShipment(shippingInformation: String) {
        createShipment(shippingInformation)
        addShippingUpdateToShipment(shippingInformation)
    }
}