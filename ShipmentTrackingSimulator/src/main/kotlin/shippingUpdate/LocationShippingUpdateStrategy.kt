package shippingUpdate

class LocationShippingUpdateStrategy(): ShippingUpdateStrategy {
    override fun updateShipment(shippingInformation: String) {
        val shippingInformationParser: ShippingInformationParser = ShippingInformationParser(shippingInformation)
        getNonNullShipment(shippingInformationParser.getShippingUpdateId()).currentLocation = shippingInformationParser.getShippingUpdateCurrentLocation()
        addShippingUpdateToShipment(shippingInformation)
    }
}