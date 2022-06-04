package subject

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shippingUpdate.CreatedShippingUpdateStrategy
import shippingUpdate.ShippingUpdateStrategy

internal class OvernightShipmentTest{
    @Test
    fun getExpressShipment(){
        val createdShippingUpdateStrategy: ShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        val thisShippingInformation: String = "created,otest,2022011010,overnight,2022011108"
        createdShippingUpdateStrategy.updateShipment(thisShippingInformation)
        val thisShipment = TrackingServer.findShipment("otest")
        assertTrue(thisShipment is OvernightShipment)
        assertTrue(thisShipment!!.shipmentType == "overnight")
    }

    @Test
    fun getShipmentLateDeliveryDate(){
        val createdShippingUpdateStrategy: ShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        val thisShippingInformation: String = "created,otest2,2022011010,overnight,2022011410"
        org.junit.jupiter.api.assertThrows<Exception> {
            createdShippingUpdateStrategy.updateShipment(thisShippingInformation)
        }
    }
}