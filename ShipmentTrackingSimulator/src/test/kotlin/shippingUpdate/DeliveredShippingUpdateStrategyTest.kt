package shippingUpdate

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shippingUpdate.CreatedShippingUpdateStrategy
import shippingUpdate.DeliveredShippingUpdateStrategy

internal class DeliveredShippingUpdateStrategyTest{
    @Test
    fun createShippingUpdate(){
        val createdShippingInformation: String = "created,desust,1234455,standard,23980323"
        val createShippingUpdateStrategy: CreatedShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        createShippingUpdateStrategy.updateShipment(createdShippingInformation)
        val deliveredShippingInformation: String = "delivered,desust,1234455"
        val deliveredShippingUpdateStrategy: DeliveredShippingUpdateStrategy = DeliveredShippingUpdateStrategy()
        deliveredShippingUpdateStrategy.updateShipment(deliveredShippingInformation)
        assertEquals(TrackingServer.findShipment("desust")!!.status, "delivered")
    }
}