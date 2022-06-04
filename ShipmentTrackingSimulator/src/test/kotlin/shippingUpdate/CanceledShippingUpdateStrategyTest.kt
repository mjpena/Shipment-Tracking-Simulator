package shippingUpdate

import TrackingServer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shippingUpdate.CanceledShippingUpdateStrategy
import shippingUpdate.CreatedShippingUpdateStrategy

internal class CanceledShippingUpdateStrategyTest{
    @Test
    fun createShippingUpdate(){
        val createdShippingInformation: String = "created,cshu,1234455,standard,20220205"
        val createShippingUpdateStrategy: CreatedShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        createShippingUpdateStrategy.updateShipment(createdShippingInformation)
        val canceledShippingInformation: String = "canceled,cshu,1234455"
        val canceledShippingUpdateStrategy: CanceledShippingUpdateStrategy = CanceledShippingUpdateStrategy()
        canceledShippingUpdateStrategy.updateShipment(canceledShippingInformation)
        assertEquals(TrackingServer.findShipment("cshu")!!.status, "canceled")
    }
}