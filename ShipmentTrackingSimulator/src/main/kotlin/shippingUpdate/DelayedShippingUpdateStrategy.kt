package shippingUpdate

import subject.Shipment

class DelayedShippingUpdateStrategy(): ShippingUpdateStrategy {
    override fun updateShipment(shippingInformation: String) {
        val shippingInformationParser: ShippingInformationParser = ShippingInformationParser(shippingInformation)
        getNonNullShipment(shippingInformationParser.getShippingUpdateId()).expectedDeliveryDate = shippingInformationParser.getShippingUpdateExpectedDeliveryDate()
        addShippingUpdateToShipment(shippingInformation)
    }
}