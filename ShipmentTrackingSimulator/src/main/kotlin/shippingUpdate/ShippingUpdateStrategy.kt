package shippingUpdate

import TrackingSimulator
import subject.Shipment

interface ShippingUpdateStrategy {
    private fun createShippingUpdate(shippingUpdatePreviousStatus: String, shippingUpdateStatus: String, shippingUpdateTimestamp: Long): ShippingUpdate{
        return ShippingUpdate(shippingUpdatePreviousStatus, shippingUpdateStatus, shippingUpdateTimestamp)
    }

    private fun getNonNullShipment(shippingUpdateId: String): Shipment{
        return TrackingSimulator.findShipment(shippingUpdateId)
            ?: throw Exception("Cannot add shipping update. Shipment not found.")
    }

    private fun getShippingUpdateStatus(shippingInformation: String): String{
        return shippingInformation.split(",")[0]
    }

    fun getShippingUpdateId(shippingInformation: String): String{
        return shippingInformation.split(",")[1]
    }

    private fun getShippingUpdateTimestamp(shippingInformation: String): Long{
        return shippingInformation.split(",")[2].toLong()
    }

    fun getShipmentWithAddedShippingUpdate(shippingInformation: String): Shipment{
        val shippingUpdateStatus: String = getShippingUpdateStatus(shippingInformation)
        val shippingUpdateId: String = getShippingUpdateId(shippingInformation)
        val shippingUpdateTimestamp: Long = getShippingUpdateTimestamp(shippingInformation)
        val shipment: Shipment = getNonNullShipment(shippingUpdateId)
        shipment.addUpdate(createShippingUpdate(shipment.status, shippingUpdateStatus, shippingUpdateTimestamp))
        return shipment
    }

    fun updateShipment(shippingInformation: String){
        getShipmentWithAddedShippingUpdate(shippingInformation)
    }
}