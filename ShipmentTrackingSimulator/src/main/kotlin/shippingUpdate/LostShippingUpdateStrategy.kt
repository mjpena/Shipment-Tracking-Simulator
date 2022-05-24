package shippingUpdate

class LostShippingUpdateStrategy(shippingInformation: String): ShippingUpdateStrategy(shippingInformation) {
    override fun updateShipment() {
        val shipment = TrackingSimulator.findShipment(shippingUpdateId)
        if (shipment == null){
            throw Exception("Cannot add shipping update. Shipment does not exist.")
        }
        else{
            shipment.addUpdate(createShippingUpdate(shipment.status))
        }
    }
}