package subject

class StandardShipment(id: String, shipmentType: String, expectedDeliveryDate: Long): Shipment(id, shipmentType){
    override var expectedDeliveryDate: Long = expectedDeliveryDate
        set(value) {
            throwErrorIfNegative(value)
            field = value
            notifyObservers()
        }

    init{
        throwErrorIfNegative(expectedDeliveryDate)
    }
}