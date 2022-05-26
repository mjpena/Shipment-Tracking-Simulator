package shippingUpdate

import TrackingSimulator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import javax.sound.midi.Track

internal class CreatedShippingUpdateStrategyTest{
    private val shippingInformation: String = "created,S00001,1234455"
    private val shippingInformationWrongValue: String = "created,S000011234455"
    private val shippingInformationWrongValue2: String = "created,S00001,hello"
    private val shippingInformationWrongValue3: String = "created,S00001,-1234455"
    private val createShippingUpdateStrategy: CreatedShippingUpdateStrategy = CreatedShippingUpdateStrategy()

    @Test
    fun createShippingUpdate(){
        createShippingUpdateStrategy.updateShipment(shippingInformation)
        assertEquals(TrackingSimulator.findShipment("S00001")!!.status, "created")
    }

    @Test
    fun getShippingUpdateId(){
        val shippingInformationParser: ShippingInformationParser = ShippingInformationParser(shippingInformation)
        assertEquals(shippingInformationParser.getShippingUpdateId(), "S00001")
    }

    @Test
    fun getShippingUpdateIdWrongValue(){
        assertThrows<Exception> {
            createShippingUpdateStrategy.updateShipment(shippingInformationWrongValue)
        }
    }

    @Test
    fun parseTimestampNotANumber(){
        assertThrows<Exception> {
            createShippingUpdateStrategy.updateShipment(shippingInformationWrongValue2)
        }
    }

    @Test
    fun parseTimestampNegativeNumber(){
        assertThrows<Exception> {
            createShippingUpdateStrategy.updateShipment(shippingInformationWrongValue3)
        }
    }
}