package shippingUpdate

import TrackingServer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shippingUpdate.CreatedShippingUpdateStrategy
import shippingUpdate.ShippedShippingUpdateStrategy

internal class ShippedShippingUpdateStrategyTest{
    @Test
    fun createShippingUpdate(){
        val createdShippingInformation: String = "created,ssust,1234455,standard,94353049"
        val createShippingUpdateStrategy: CreatedShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        createShippingUpdateStrategy.updateShipment(createdShippingInformation)
        val shippedShippingInformation: String = "shipped,ssust,123455,234909849"
        val shippedShippingUpdateStrategy: ShippedShippingUpdateStrategy = ShippedShippingUpdateStrategy()
        shippedShippingUpdateStrategy.updateShipment(shippedShippingInformation)
        assertEquals(TrackingServer.findShipment("ssust")!!.status, "shipped")
        assertEquals(TrackingServer.findShipment("ssust")!!.expectedDeliveryDate, 234909849)
    }
}