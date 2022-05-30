package shippingUpdate

class EarlyShippingUpdateStrategy: ShippingUpdateStrategy {
    override fun updateShipment(shippingInformation: String) {
        val shippingInformationParser: ShippingInformationParser = ShippingInformationParser(shippingInformation)
        getNonNullShipment(shippingInformationParser.getShippingUpdateId()).expectedDeliveryDate = shippingInformationParser.getShippingUpdateExpectedDeliveryDate()
        addShippingUpdateToShipment(shippingInformation)
    }
}