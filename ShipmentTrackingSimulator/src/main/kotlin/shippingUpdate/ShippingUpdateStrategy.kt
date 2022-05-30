package shippingUpdate

import TrackingSimulator
import subject.ShipmentFactory

interface ShippingUpdateStrategy {
    private fun createShippingUpdate(shippingUpdatePreviousStatus: String, shippingUpdateStatus: String, shippingUpdateTimestamp: Long): ShippingUpdate{
        return ShippingUpdate(shippingUpdatePreviousStatus, shippingUpdateStatus, shippingUpdateTimestamp)
    }

    fun getNonNullShipment(shippingUpdateId: String): ShipmentFactory{
        return TrackingSimulator.findShipment(shippingUpdateId)
            ?: throw Exception("Cannot add shipping update. Shipment not found.")
    }

    fun addShippingUpdateToShipment(shippingInformation: String): ShipmentFactory{
        val shippingInformationParser: ShippingInformationParser = ShippingInformationParser(shippingInformation)
        val shippingUpdateStatus: String = shippingInformationParser.getShippingUpdateStatus()
        val shippingUpdateId: String = shippingInformationParser.getShippingUpdateId()
        val shippingUpdateTimestamp: Long = shippingInformationParser.getShippingUpdateTimestamp()
        val shipment = getNonNullShipment(shippingUpdateId)
        shipment.addUpdate(createShippingUpdate(shipment.status, shippingUpdateStatus, shippingUpdateTimestamp))
        return shipment
    }

    fun updateShipment(shippingInformation: String){
        addShippingUpdateToShipment(shippingInformation)
    }
}