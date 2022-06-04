package shippingUpdate

import TrackingServer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import shippingUpdate.CreatedShippingUpdateStrategy

internal class CreatedShippingUpdateStrategyTest{
    private val shippingInformation: String = "created,csust,1234455,standard,20220205"
    private val shippingInformationWrongValue: String = "created,csust2"
    private val shippingInformationWrongValue2: String = "created,csust3,-1234455"
    private val createShippingUpdateStrategy: CreatedShippingUpdateStrategy = CreatedShippingUpdateStrategy()

    @Test
    fun createShipmentUpdate(){
        createShippingUpdateStrategy.updateShipment(shippingInformation)
        assertEquals(TrackingServer.findShipment("csust")!!.status, "created")
        assertEquals(TrackingServer.findShipment("csust")!!.shipmentType, "standard")

    }

    @Test
    fun getShippingUpdateIdWrongValue(){
        assertThrows<Exception> {
            createShippingUpdateStrategy.updateShipment(shippingInformationWrongValue)
        }
    }

    @Test
    fun parseTimestampNegativeNumber(){
        assertThrows<Exception> {
            createShippingUpdateStrategy.updateShipment(shippingInformationWrongValue2)
        }
    }
}