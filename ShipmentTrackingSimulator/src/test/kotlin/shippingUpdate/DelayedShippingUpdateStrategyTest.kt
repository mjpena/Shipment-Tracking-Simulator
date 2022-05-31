package shippingUpdate

import TrackingServer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import shippingUpdate.CreatedShippingUpdateStrategy
import shippingUpdate.DelayedShippingUpdateStrategy

internal class DelayedShippingUpdateStrategyTest{
    @Test
    fun createShippingUpdate(){
        val createdShippingInformation: String = "created,S00001,1234455"
        val createShippingUpdateStrategy: CreatedShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        createShippingUpdateStrategy.updateShipment(createdShippingInformation)
        val delayedShippingInformation: String = "delayed,S00001,1234455,1233334"
        val delayedShippingUpdateStrategy: DelayedShippingUpdateStrategy = DelayedShippingUpdateStrategy()
        delayedShippingUpdateStrategy.updateShipment(delayedShippingInformation)
        assertEquals(TrackingServer.findShipment("S00001")!!.status, "delayed")
        assertEquals(TrackingServer.findShipment("S00001")!!.expectedDeliveryDate, 1233334)
    }

    @Test
    fun createShippingUpdateNoExpectedDeliveryDate(){
        val createdShippingInformation: String = "created,S00001,1234455"
        val createShippingUpdateStrategy: CreatedShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        createShippingUpdateStrategy.updateShipment(createdShippingInformation)
        val delayedShippingInformation: String = "delayed,S00001,1234455"
        val delayedShippingUpdateStrategy: DelayedShippingUpdateStrategy = DelayedShippingUpdateStrategy()
        assertThrows<Exception>{
            delayedShippingUpdateStrategy.updateShipment(delayedShippingInformation)
        }
        assertEquals(TrackingServer.findShipment("S00001")!!.status, "created")
    }

    @Test
    fun getNonexistantShipment(){
        val delayedShippingInformation: String = "delayed,S00001,1234455"
        val delayedShippingUpdateStrategy: DelayedShippingUpdateStrategy = DelayedShippingUpdateStrategy()
        assertThrows<Exception> {
            delayedShippingUpdateStrategy.updateShipment(delayedShippingInformation)
        }
    }

    @Test
    fun incorrectShippingInformation(){
        val delayedShippingInformation: String = "delayedS00001,1234455"
        val delayedShippingUpdateStrategy: DelayedShippingUpdateStrategy = DelayedShippingUpdateStrategy()
        assertThrows<Exception> {
            delayedShippingUpdateStrategy.updateShipment(delayedShippingInformation)
        }
    }
}