package shippingUpdate

import TrackingSimulator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import javax.sound.midi.Track

internal class CreatedShippingUpdateStrategyTest{
    private val shippingInformation: String = "created,S00001,1234455"
    private val shippingInformationWrongValue: String = "created,S000011234455"
    private val shippingInformationWrongValue2: String = "created,S00001,-1234455"
    private val createShippingUpdateStrategy: CreatedShippingUpdateStrategy = CreatedShippingUpdateStrategy()

    @Test
    fun createShipmentUpdate(){
        createShippingUpdateStrategy.updateShipment(shippingInformation)
        assertEquals(TrackingSimulator.findShipment("S00001")!!.status, "created")
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