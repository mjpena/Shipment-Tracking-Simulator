package shippingUpdate

import TrackingSimulator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ShippedShippingUpdateStrategyTest{
    @Test
    fun createShippingUpdate(){
        val createdShippingInformation: String = "created,S00001,1234455"
        val createShippingUpdateStrategy: CreatedShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        createShippingUpdateStrategy.updateShipment(createdShippingInformation)
        val shippedShippingInformation: String = "shipped,S00001,123455,234909849"
        val shippedShippingUpdateStrategy: ShippedShippingUpdateStrategy = ShippedShippingUpdateStrategy()
        shippedShippingUpdateStrategy.updateShipment(shippedShippingInformation)
        assertEquals(TrackingSimulator.findShipment("S00001")!!.status, "shipped")
        assertEquals(TrackingSimulator.findShipment("S00001")!!.expectedDeliveryDate, 234909849)
    }
}