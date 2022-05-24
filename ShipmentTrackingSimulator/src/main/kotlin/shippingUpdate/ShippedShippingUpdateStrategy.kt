package shippingUpdate

import TrackingSimulator
import subject.Shipment

class ShippedShippingUpdateStrategy(): ShippingUpdateStrategy {
    private fun getShippingUpdateExpectedDeliveryDate(shippingInformation: String): Long{
        return shippingInformation.split(",")[3].toLong()
    }

    override fun updateShipment(shippingInformation: String) {
        getShipmentWithAddedShippingUpdate(shippingInformation).expectedDeliveryDate = getShippingUpdateExpectedDeliveryDate(shippingInformation)
    }
}