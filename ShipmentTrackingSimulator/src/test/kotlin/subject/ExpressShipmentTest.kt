package subject

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shippingUpdate.CreatedShippingUpdateStrategy
import shippingUpdate.ShippingUpdateStrategy

internal class ExpressShipmentTest{
    private val createdShippingUpdateStrategy: ShippingUpdateStrategy = CreatedShippingUpdateStrategy()

    @Test
    fun getExpressShipment(){
        val thisShippingInformation: String = "created,etest,2022011010,express,2022011210"
        createdShippingUpdateStrategy.updateShipment(thisShippingInformation)
        val thisShipment = TrackingServer.findShipment("etest")
        assertTrue(thisShipment is ExpressShipment)
        assertTrue(thisShipment!!.shipmentType == "express")
    }

    @Test
    fun getShipmentLateDeliveryDate(){
        val thisShippingInformation: String = "created,etest2,2022011010,express,2022011410"
        org.junit.jupiter.api.assertThrows<Exception> {
            createdShippingUpdateStrategy.updateShipment(thisShippingInformation)
        }
    }

    @Test
    fun modifyDeliveryDate(){
        val thisShippingInformation: String = "created,etest3,2022011010,express,2022011210"
        createdShippingUpdateStrategy.updateShipment(thisShippingInformation)
        val thisShipment = TrackingServer.findShipment("etest3")
        assertTrue(thisShipment is ExpressShipment)
        assertTrue(thisShipment!!.shipmentType == "express")
        thisShipment.expectedDeliveryDate = 2022011410
        assertTrue(thisShipment!!.shipmentType == "express - updated to delivery date later than 3 days after it was created")
        thisShipment.expectedDeliveryDate = 2022011210
        assertTrue(thisShipment!!.shipmentType == "express")
    }
}