package subject

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shippingUpdate.CreatedShippingUpdateStrategy
import shippingUpdate.ShippingUpdateStrategy

internal class BulkShipmentTest{
    private val createdShippingUpdateStrategy: ShippingUpdateStrategy = CreatedShippingUpdateStrategy()

    @Test
    fun getBulkShipment(){
        val thisShippingInformation: String = "created,btest,2022011010,bulk,2022011310"
        createdShippingUpdateStrategy.updateShipment(thisShippingInformation)
        val thisShipment = TrackingServer.findShipment("btest")
        assertTrue(thisShipment is BulkShipment)
        assertTrue(thisShipment!!.shipmentType == "bulk")
    }

    @Test
    fun getShipmentLateDeliveryDate(){
        val thisShippingInformation: String = "created,btest2,2022011010,bulk,2022011110"
        org.junit.jupiter.api.assertThrows<Exception> {
            createdShippingUpdateStrategy.updateShipment(thisShippingInformation)
        }
    }

    @Test
    fun modifyDeliveryDate(){
        val thisShippingInformation: String = "created,btest3,2022011010,bulk,2022011310"
        createdShippingUpdateStrategy.updateShipment(thisShippingInformation)
        val thisShipment = TrackingServer.findShipment("btest3")
        assertTrue(thisShipment is BulkShipment)
        assertTrue(thisShipment!!.shipmentType == "bulk")
        thisShipment.expectedDeliveryDate = 2022011110
        assertTrue(thisShipment!!.shipmentType == "bulk - updated to delivery date earlier than 3 days after it was created")
        thisShipment.expectedDeliveryDate = 2022011310
        assertTrue(thisShipment!!.shipmentType == "bulk")
    }
}