package subject

class ExpressShipment(id: String, shipmentType: String, createdTimestamp: Long, expectedDeliveryDate: Long): Shipment(id, shipmentType){
    private val createdTimestamp: Long = createdTimestamp
    override var expectedDeliveryDate: Long = expectedDeliveryDate
        set(value) {
            throwErrorIfNegative(value)
            shipmentType = if (value - createdTimestamp > 300){
                "express - updated to delivery date later than 3 days after it was created"
            } else{
                "express"
            }

            field = value
            notifyObservers()
        }

    init {
        throwErrorIfNegative(expectedDeliveryDate)
        if (expectedDeliveryDate - createdTimestamp > 300){
            throw Exception("Express shipment cannot have an expected delivery date of more than 3 days after the shipment was created.")
        }
    }
}