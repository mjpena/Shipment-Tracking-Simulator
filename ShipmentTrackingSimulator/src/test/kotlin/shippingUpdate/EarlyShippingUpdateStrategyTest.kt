package shippingUpdate

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import shippingUpdate.CreatedShippingUpdateStrategy
import shippingUpdate.EarlyShippingUpdateStrategy

internal class EarlyShippingUpdateStrategyTest {
    @Test
    fun createShippingUpdate(){
        val createdShippingInformation: String = "created,esust,1234455,standard,94353049"
        val createShippingUpdateStrategy: CreatedShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        createShippingUpdateStrategy.updateShipment(createdShippingInformation)
        val earlyShippingInformation: String = "early,esust,1234455,1233334"
        val earlyShippingUpdateStrategy: EarlyShippingUpdateStrategy = EarlyShippingUpdateStrategy()
        earlyShippingUpdateStrategy.updateShipment(earlyShippingInformation)
        assertEquals(TrackingServer.findShipment("esust")!!.status, "early")
        assertEquals(TrackingServer.findShipment("esust")!!.expectedDeliveryDate, 1233334)
    }

    @Test
    fun createShippingUpdateNoExpectedDeliveryDate(){
        val createdShippingInformation: String = "created,esust2,1234455,standard,94353049"
        val createShippingUpdateStrategy: CreatedShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        createShippingUpdateStrategy.updateShipment(createdShippingInformation)
        val earlyShippingInformation: String = "early,esust2,1234455"
        val earlyShippingUpdateStrategy: EarlyShippingUpdateStrategy = EarlyShippingUpdateStrategy()
        assertThrows<Exception>{
            earlyShippingUpdateStrategy.updateShipment(earlyShippingInformation)
        }
        assertEquals(TrackingServer.findShipment("esust2")!!.status, "created")
    }

    @Test
    fun getNonexistantShipment(){
        val earlyShippingInformation: String = "early,esust3,1234455"
        val earlyShippingUpdateStrategy: EarlyShippingUpdateStrategy = EarlyShippingUpdateStrategy()
        assertThrows<Exception> {
            earlyShippingUpdateStrategy.updateShipment(earlyShippingInformation)
        }
    }

    @Test
    fun incorrectShippingInformation(){
        val earlyShippingInformation: String = "earlyS00003,1234455"
        val earlyShippingUpdateStrategy: EarlyShippingUpdateStrategy = EarlyShippingUpdateStrategy()
        assertThrows<Exception> {
            earlyShippingUpdateStrategy.updateShipment(earlyShippingInformation)
        }
    }
}