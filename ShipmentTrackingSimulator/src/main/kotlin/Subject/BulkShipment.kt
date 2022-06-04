package subject

class BulkShipment(id: String, shipmentType: String, updateTimeStamp: Long, expectedDeliveryDate: Long): Shipment(id, shipmentType, expectedDeliveryDate) {
    init {
        if (expectedDeliveryDate - updateTimeStamp < 300){
            throw Exception("Bulk shipment should not have an expected delivery date of less than 3 days after the shipment was created.")
        }
    }
}