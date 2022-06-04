package subject

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shippingUpdate.CreatedShippingUpdateStrategy
import shippingUpdate.ShippingUpdateStrategy

internal class StandardShipmentTest{
    @Test
    fun standardShipment(){
        val createdShippingUpdateStrategy: ShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        val thisShippingInformation: String = "created,stest,3352353,standard,23433"
        createdShippingUpdateStrategy.updateShipment(thisShippingInformation)
        val thisShipment = TrackingServer.findShipment("stest")
        assertTrue(thisShipment is StandardShipment)
        assertTrue(thisShipment!!.shipmentType == "standard")
    }
}