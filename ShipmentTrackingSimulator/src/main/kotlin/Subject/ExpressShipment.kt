package subject

class ExpressShipment(id: String, shipmentType: String, updateTimeStamp: Long, expectedDeliveryDate: Long): Shipment(id, shipmentType, expectedDeliveryDate) {
    init {
        if (expectedDeliveryDate - updateTimeStamp > 300){
            throw Exception("Express shipment cannot have an expected delivery date of more than 3 days after the shipment was created.")
        }
    }
}