package subject

class OvernightShipment(id: String, shipmentType: String, createdTimestamp: Long, expectedDeliveryDate: Long): Shipment(id, shipmentType){
    private val createdTimestamp: Long = createdTimestamp
    override var expectedDeliveryDate: Long = expectedDeliveryDate
        set(value) {
            throwErrorIfNegative(value)
            shipmentType = if (value - createdTimestamp > 100){
                "overnight - updated to delivery date later than 24 hours after it was created"
            } else{
                "overnight"
            }

            field = value
            notifyObservers()
        }

    init {
        throwErrorIfNegative(expectedDeliveryDate)
        if (expectedDeliveryDate - createdTimestamp > 100){
            throw Exception("Overnight shipment must have an expected delivery date within 24 hours after it was created.")
        }
    }
}