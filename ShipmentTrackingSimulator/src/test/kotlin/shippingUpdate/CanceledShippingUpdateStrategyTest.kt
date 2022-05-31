package shippingUpdate

import TrackingServer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shippingUpdate.CanceledShippingUpdateStrategy
import shippingUpdate.CreatedShippingUpdateStrategy

internal class CanceledShippingUpdateStrategyTest{
    @Test
    fun createShippingUpdate(){
        val createdShippingInformation: String = "created,S00001,1234455,standard"
        val createShippingUpdateStrategy: CreatedShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        createShippingUpdateStrategy.updateShipment(createdShippingInformation)
        val canceledShippingInformation: String = "canceled,S00001,1234455"
        val canceledShippingUpdateStrategy: CanceledShippingUpdateStrategy = CanceledShippingUpdateStrategy()
        canceledShippingUpdateStrategy.updateShipment(canceledShippingInformation)
        assertEquals(TrackingServer.findShipment("S00001")!!.status, "canceled")
    }
}