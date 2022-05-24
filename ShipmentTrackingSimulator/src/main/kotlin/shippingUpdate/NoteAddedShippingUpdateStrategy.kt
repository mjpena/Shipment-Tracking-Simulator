package shippingUpdate

class NoteAddedShippingUpdateStrategy(): ShippingUpdateStrategy {
    private fun getShippingUpdateNote(shippingInformation: String): String{
        return shippingInformation.split(",")[3]
    }

    override fun updateShipment(shippingInformation: String) {
        getShipmentWithAddedShippingUpdate(shippingInformation).addNote(getShippingUpdateNote(shippingInformation))
    }
}