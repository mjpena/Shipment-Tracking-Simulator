package shippingUpdate

import TrackingSimulator
import subject.Shipment

interface ShippingUpdateStrategy {
    private fun createShippingUpdate(shippingUpdatePreviousStatus: String, shippingUpdateStatus: String, shippingUpdateTimestamp: Long): ShippingUpdate{
        return ShippingUpdate(shippingUpdatePreviousStatus, shippingUpdateStatus, shippingUpdateTimestamp)
    }

    fun getNonNullShipment(shippingUpdateId: String): Shipment{
        return TrackingSimulator.findShipment(shippingUpdateId)
            ?: throw Exception("Cannot add shipping update. Shipment not found.")
    }

    fun parserChecker(shippingInformation: String , indexCount: Int): String{
        if (shippingInformation.split(",").size < indexCount + 1){
            throw Exception("Could not parse shipping information. Index out of bounds.")
        }
        return shippingInformation.split(",")[indexCount]
    }

    private fun getShippingUpdateStatus(shippingInformation: String): String{
        return parserChecker(shippingInformation, 0)
    }

    fun getShippingUpdateId(shippingInformation: String): String{
        return parserChecker(shippingInformation, 1)
    }

    private fun getShippingUpdateTimestamp(shippingInformation: String): Long{
        return parserChecker(shippingInformation, 2).toLong()
    }

    fun addShippingUpdateToShipment(shippingInformation: String): Shipment{
        val shippingUpdateStatus: String = getShippingUpdateStatus(shippingInformation)
        val shippingUpdateId: String = getShippingUpdateId(shippingInformation)
        val shippingUpdateTimestamp: Long = getShippingUpdateTimestamp(shippingInformation)
        val shipment = getNonNullShipment(shippingUpdateId)
        shipment.addUpdate(createShippingUpdate(shipment.status, shippingUpdateStatus, shippingUpdateTimestamp))
        return shipment
    }

    fun updateShipment(shippingInformation: String){
        addShippingUpdateToShipment(shippingInformation)
    }
}