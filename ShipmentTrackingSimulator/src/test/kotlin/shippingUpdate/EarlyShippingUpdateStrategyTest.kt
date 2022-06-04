package shippingUpdate

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import shippingUpdate.CreatedShippingUpdateStrategy
import shippingUpdate.EarlyShippingUpdateStrategy

internal class EarlyShippingUpdateStrategyTest {
    @Test
    fun createShippingUpdate(){
        val createdShippingInformation: String = "created,S00001,1234455,standard,94353049"
        val createShippingUpdateStrategy: CreatedShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        createShippingUpdateStrategy.updateShipment(createdShippingInformation)
        val earlyShippingInformation: String = "early,S00001,1234455,1233334"
        val earlyShippingUpdateStrategy: EarlyShippingUpdateStrategy = EarlyShippingUpdateStrategy()
        earlyShippingUpdateStrategy.updateShipment(earlyShippingInformation)
        assertEquals(TrackingServer.findShipment("S00001")!!.status, "early")
        assertEquals(TrackingServer.findShipment("S00001")!!.expectedDeliveryDate, 1233334)
    }

    @Test
    fun createShippingUpdateNoExpectedDeliveryDate(){
        val createdShippingInformation: String = "created,S00001,1234455,standard,94353049"
        val createShippingUpdateStrategy: CreatedShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        createShippingUpdateStrategy.updateShipment(createdShippingInformation)
        val earlyShippingInformation: String = "early,S00001,1234455"
        val earlyShippingUpdateStrategy: EarlyShippingUpdateStrategy = EarlyShippingUpdateStrategy()
        assertThrows<Exception>{
            earlyShippingUpdateStrategy.updateShipment(earlyShippingInformation)
        }
        assertEquals(TrackingServer.findShipment("S00001")!!.status, "created")
    }

    @Test
    fun getNonexistantShipment(){
        val earlyShippingInformation: String = "early,S00001,1234455"
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