package shippingUpdate

import subject.Shipment

class LocationShippingUpdateStrategy(): ShippingUpdateStrategy {
    private fun getShippingUpdateCurrentLocation(shippingInformation: String): String{
        return shippingInformation.split(",")[3]
    }

    override fun updateShipment(shippingInformation: String) {
        getShipmentWithAddedShippingUpdate(shippingInformation).currentLocation = getShippingUpdateCurrentLocation(shippingInformation)
    }
}