package subject

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shippingUpdate.CreatedShippingUpdateStrategy
import shippingUpdate.ShippingUpdateStrategy

internal class BulkShipmentTest{
    @Test
    fun getBulkShipment(){
        val createdShippingUpdateStrategy: ShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        val thisShippingInformation: String = "created,btest,2022011010,bulk,2022011310"
        createdShippingUpdateStrategy.updateShipment(thisShippingInformation)
        val thisShipment = TrackingServer.findShipment("btest")
        assertTrue(thisShipment is BulkShipment)
        assertTrue(thisShipment!!.shipmentType == "bulk")
    }

    @Test
    fun getShipmentLateDeliveryDate(){
        val createdShippingUpdateStrategy: ShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        val thisShippingInformation: String = "created,btest2,2022011010,bulk,2022011110"
        org.junit.jupiter.api.assertThrows<Exception> {
            createdShippingUpdateStrategy.updateShipment(thisShippingInformation)
        }
    }
}