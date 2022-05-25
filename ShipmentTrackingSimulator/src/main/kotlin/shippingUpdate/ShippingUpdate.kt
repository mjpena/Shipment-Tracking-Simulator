package shippingUpdate

class ShippingUpdate(previousShippingInformationStatus: String, shippingInformationStatus: String, shippingInformationTimestamp: Long) {
    val previousStatus: String = previousShippingInformationStatus
    val newStatus: String = shippingInformationStatus
    val timeStamp: Long

    init{
        if (shippingInformationTimestamp < 0){
            throw Exception("Expected delivery date cannot be a negative time.")
        }
        timeStamp = shippingInformationTimestamp
    }

    override fun toString(): String {
        return "Shipment went from $previousStatus to $newStatus on ${timeStamp}."
    }
}