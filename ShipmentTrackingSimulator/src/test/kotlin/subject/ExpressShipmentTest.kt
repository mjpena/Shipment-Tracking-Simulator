package subject

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shippingUpdate.CreatedShippingUpdateStrategy
import shippingUpdate.ShippingUpdateStrategy

internal class ExpressShipmentTest{
    @Test
    fun getExpressShipment(){
        val createdShippingUpdateStrategy: ShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        val thisShippingInformation: String = "created,etest,2022011010,express,2022011210"
        createdShippingUpdateStrategy.updateShipment(thisShippingInformation)
        val thisShipment = TrackingServer.findShipment("etest")
        assertTrue(thisShipment is ExpressShipment)
        assertTrue(thisShipment!!.shipmentType == "express")
    }

    @Test
    fun getShipmentLateDeliveryDate(){
        val createdShippingUpdateStrategy: ShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        val thisShippingInformation: String = "created,etest2,2022011010,express,2022011410"
        org.junit.jupiter.api.assertThrows<Exception> {
            createdShippingUpdateStrategy.updateShipment(thisShippingInformation)
        }
    }
}