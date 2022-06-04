package subject

class OvernightShipment(id: String, shipmentType: String, updateTimeStamp: Long, expectedDeliveryDate: Long): Shipment(id, shipmentType, expectedDeliveryDate) {
    init {
        if (expectedDeliveryDate - updateTimeStamp > 100){
            throw Exception("Overnight shipment must have an expected delivery date of the day after it was created.")
        }
    }
}