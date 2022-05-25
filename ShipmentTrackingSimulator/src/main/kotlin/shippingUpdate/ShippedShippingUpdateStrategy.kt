package shippingUpdate

import TrackingSimulator
import subject.Shipment

class ShippedShippingUpdateStrategy(): ShippingUpdateStrategy {
    private fun getShippingUpdateExpectedDeliveryDate(shippingInformation: String): Long{
        return parserChecker(shippingInformation, 3).toLong()
    }

    override fun updateShipment(shippingInformation: String) {
        getNonNullShipment(getShippingUpdateId(shippingInformation)).expectedDeliveryDate = getShippingUpdateExpectedDeliveryDate(shippingInformation)
        addShippingUpdateToShipment(shippingInformation)
    }
}