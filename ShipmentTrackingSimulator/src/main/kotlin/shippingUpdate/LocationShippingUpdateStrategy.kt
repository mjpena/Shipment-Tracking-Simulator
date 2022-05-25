package shippingUpdate

import subject.Shipment

class LocationShippingUpdateStrategy(): ShippingUpdateStrategy {
    private fun getShippingUpdateCurrentLocation(shippingInformation: String): String{
        return shippingInformation.split(",")[3]
    }

    override fun updateShipment(shippingInformation: String) {
        getNonNullShipment(getShippingUpdateId(shippingInformation)).currentLocation = getShippingUpdateCurrentLocation(shippingInformation)
        addShippingUpdateToShipment(shippingInformation)
    }
}