package shippingUpdate

import TrackingSimulator
import subject.Shipment

abstract class ShippingUpdateStrategy(protected val shippingInformation: String) {
    protected var shippingUpdateStatus: String = ""
    protected var shippingUpdateId: String = ""
    protected var shippingUpdateTimestamp: Long = 0

    init{
        // todo: error catching
        shippingUpdateStatus = shippingInformation.split(",")[0]
        shippingUpdateId = shippingInformation.split(",")[1]
        shippingUpdateTimestamp = shippingInformation.split(",")[2].toLong()
    }

    fun createShippingUpdate(previousShippingStatus: String): ShippingUpdate{
        return ShippingUpdate(previousShippingStatus, shippingUpdateStatus, shippingUpdateTimestamp)
    }

    abstract fun updateShipment()
}