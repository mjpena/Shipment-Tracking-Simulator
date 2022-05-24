package shippingUpdate

import subject.Shipment

class DelayedShippingUpdateStrategy(): ShippingUpdateStrategy {
    private fun getShippingUpdateExpectedDeliveryDate(shippingInformation: String): Long{
        return shippingInformation.split(",")[3].toLong()
    }

    override fun updateShipment(shippingInformation: String) {
        getShipmentWithAddedShippingUpdate(shippingInformation).expectedDeliveryDate = getShippingUpdateExpectedDeliveryDate(shippingInformation)
    }
}