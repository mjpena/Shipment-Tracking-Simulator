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
        val createdShippingInformation: String = "created,dsust,1234455,standard,49038953"
        val createShippingUpdateStrategy: CreatedShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        createShippingUpdateStrategy.updateShipment(createdShippingInformation)
        val delayedShippingInformation: String = "delayed,dsust,1234455,1233334"
        val delayedShippingUpdateStrategy: DelayedShippingUpdateStrategy = DelayedShippingUpdateStrategy()
        delayedShippingUpdateStrategy.updateShipment(delayedShippingInformation)
        assertEquals(TrackingServer.findShipment("dsust")!!.status, "delayed")
        assertEquals(TrackingServer.findShipment("dsust")!!.expectedDeliveryDate, 1233334)
    }

    @Test
    fun createShippingUpdateNoExpectedDeliveryDate(){
        val createdShippingInformation: String = "created,dsust2,1234455,standard,49038953"
        val createShippingUpdateStrategy: CreatedShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        createShippingUpdateStrategy.updateShipment(createdShippingInformation)
        val delayedShippingInformation: String = "delayed,dsust2,1234455"
        val delayedShippingUpdateStrategy: DelayedShippingUpdateStrategy = DelayedShippingUpdateStrategy()
        assertThrows<Exception>{
            delayedShippingUpdateStrategy.updateShipment(delayedShippingInformation)
        }
        assertEquals(TrackingServer.findShipment("dsust2")!!.status, "created")
    }

    @Test
    fun getNonexistantShipment(){
        val delayedShippingInformation: String = "delayed,dsust3,1234455"
        val delayedShippingUpdateStrategy: DelayedShippingUpdateStrategy = DelayedShippingUpdateStrategy()
        assertThrows<Exception> {
            delayedShippingUpdateStrategy.updateShipment(delayedShippingInformation)
        }
    }

    @Test
    fun incorrectShippingInformation(){
        val delayedShippingInformation: String = "delayeddsust4,1234455"
        val delayedShippingUpdateStrategy: DelayedShippingUpdateStrategy = DelayedShippingUpdateStrategy()
        assertThrows<Exception> {
            delayedShippingUpdateStrategy.updateShipment(delayedShippingInformation)
        }
    }
}