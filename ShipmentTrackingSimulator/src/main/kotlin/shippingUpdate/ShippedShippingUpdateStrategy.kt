package shippingUpdate

import TrackingSimulator
import subject.Shipment

class ShippedShippingUpdateStrategy(): ShippingUpdateStrategy {
    override fun updateShipment(shippingInformation: String) {
        val shippingInformationParser: ShippingInformationParser = ShippingInformationParser(shippingInformation)
        getNonNullShipment(shippingInformationParser.getShippingUpdateId()).expectedDeliveryDate = shippingInformationParser.getShippingUpdateExpectedDeliveryDate()
        addShippingUpdateToShipment(shippingInformation)
    }
}