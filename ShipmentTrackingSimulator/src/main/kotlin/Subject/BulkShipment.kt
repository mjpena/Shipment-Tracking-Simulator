package subject

class BulkShipment(id: String, shipmentType: String, createdTimestamp: Long, expectedDeliveryDate: Long): Shipment(id, shipmentType){
    private val createdTimestamp: Long = createdTimestamp
    override var expectedDeliveryDate: Long = expectedDeliveryDate
        set(value) {
            throwErrorIfNegative(value)
            shipmentType = if (value - createdTimestamp < 300){
                "bulk - updated to delivery date earlier than 3 days after it was created"
            } else{
                "bulk"
            }

            field = value
            notifyObservers()
        }

    init {
        throwErrorIfNegative(expectedDeliveryDate)
        if (expectedDeliveryDate - createdTimestamp < 300){
            throw Exception("Bulk shipment should not have an expected delivery date of less than 3 days after the shipment was created.")
        }
    }
}