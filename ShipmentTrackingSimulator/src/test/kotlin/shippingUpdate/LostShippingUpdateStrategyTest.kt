package shippingUpdate

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shippingUpdate.CreatedShippingUpdateStrategy
import shippingUpdate.LostShippingUpdateStrategy

internal class LostShippingUpdateStrategyTest{
    @Test
    fun createShippingUpdate(){
        val createdShippingInformation: String = "created,S00001,1234455,standard,94353049"
        val createShippingUpdateStrategy: CreatedShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        createShippingUpdateStrategy.updateShipment(createdShippingInformation)
        val lostShippingInformation: String = "lost,S00001,1234455"
        val lostShippingUpdateStrategy: LostShippingUpdateStrategy = LostShippingUpdateStrategy()
        lostShippingUpdateStrategy.updateShipment(lostShippingInformation)
        assertEquals(TrackingServer.findShipment("S00001")!!.status, "lost")
    }
}