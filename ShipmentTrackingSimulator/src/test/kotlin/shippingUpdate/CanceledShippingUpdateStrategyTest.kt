package shippingUpdate

import TrackingSimulator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CanceledShippingUpdateStrategyTest{
    @Test
    fun createShippingUpdate(){
        val createdShippingInformation: String = "created,S00001,1234455"
        val createShippingUpdateStrategy: CreatedShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        createShippingUpdateStrategy.updateShipment(createdShippingInformation)
        val canceledShippingInformation: String = "canceled,S00001,1234455"
        val canceledShippingUpdateStrategy: CanceledShippingUpdateStrategy = CanceledShippingUpdateStrategy()
        canceledShippingUpdateStrategy.updateShipment(canceledShippingInformation)
        assertEquals(TrackingSimulator.findShipment("S00001")!!.status, "canceled")
    }
}