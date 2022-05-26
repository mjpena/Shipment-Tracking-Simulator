package shippingUpdate

class NoteAddedShippingUpdateStrategy(): ShippingUpdateStrategy {
    override fun updateShipment(shippingInformation: String) {
        val shippingInformationParser: ShippingInformationParser = ShippingInformationParser(shippingInformation)
        getNonNullShipment(shippingInformationParser.getShippingUpdateId()).notes.add(shippingInformationParser.getShippingUpdateNote())
        addShippingUpdateToShipment(shippingInformation)
    }
}