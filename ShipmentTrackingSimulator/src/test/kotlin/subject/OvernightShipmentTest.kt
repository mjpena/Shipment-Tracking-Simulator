package subject

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shippingUpdate.CreatedShippingUpdateStrategy
import shippingUpdate.ShippingUpdateStrategy

internal class OvernightShipmentTest{
    private val createdShippingUpdateStrategy: ShippingUpdateStrategy = CreatedShippingUpdateStrategy()

    @Test
    fun getExpressShipment(){
        val thisShippingInformation: String = "created,otest,2022011010,overnight,2022011108"
        createdShippingUpdateStrategy.updateShipment(thisShippingInformation)
        val thisShipment = TrackingServer.findShipment("otest")
        assertTrue(thisShipment is OvernightShipment)
        assertTrue(thisShipment!!.shipmentType == "overnight")
    }

    @Test
    fun getShipmentLateDeliveryDate(){
        val thisShippingInformation: String = "created,otest2,2022011010,overnight,2022011410"
        org.junit.jupiter.api.assertThrows<Exception> {
            createdShippingUpdateStrategy.updateShipment(thisShippingInformation)
        }
    }

    @Test
    fun modifyDeliveryDate(){
        val thisShippingInformation: String = "created,otest3,2022011010,overnight,2022011108"
        createdShippingUpdateStrategy.updateShipment(thisShippingInformation)
        val thisShipment = TrackingServer.findShipment("otest3")
        assertTrue(thisShipment is OvernightShipment)
        assertTrue(thisShipment!!.shipmentType == "overnight")
        thisShipment.expectedDeliveryDate = 2022011410
        assertTrue(thisShipment!!.shipmentType == "overnight - updated to delivery date later than 24 hours after it was created")
        thisShipment.expectedDeliveryDate = 2022011108
        assertTrue(thisShipment!!.shipmentType == "overnight")
    }
}