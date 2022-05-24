package shippingUpdate

class ShippingUpdate(previousShippingInformationStatus: String, shippingInformationStatus: String, shippingInformationTimestamp: Long) {
    var previousStatus: String = previousShippingInformationStatus
    var newStatus: String = shippingInformationStatus
    var timeStamp: Long = shippingInformationTimestamp
}