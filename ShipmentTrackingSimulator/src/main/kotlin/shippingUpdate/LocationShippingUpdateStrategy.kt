package shippingUpdate

class LocationShippingUpdateStrategy(shippingInformation: String): ShippingUpdateStrategy(shippingInformation) {
    override fun updateShipment() {
        val shipment = TrackingSimulator.findShipment(shippingUpdateId)
        if (shipment == null){
            throw Exception("Cannot add shipping update. Shipment does not exist.")
        }
        else{
            shipment.currentLocation = shippingInformation.split(",")[3]
            shipment.addUpdate(createShippingUpdate(shipment.status))
        }
    }
}