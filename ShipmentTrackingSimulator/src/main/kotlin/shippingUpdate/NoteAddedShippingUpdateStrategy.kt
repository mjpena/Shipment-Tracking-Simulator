package shippingUpdate

class NoteAddedShippingUpdateStrategy(): ShippingUpdateStrategy {
    private fun getShippingUpdateNote(shippingInformation: String): String{
        return shippingInformation.split(",")[3]
    }

    override fun updateShipment(shippingInformation: String) {
        getNonNullShipment(getShippingUpdateId(shippingInformation)).notes.add(getShippingUpdateNote(shippingInformation))
        addShippingUpdateToShipment(shippingInformation)
    }
}