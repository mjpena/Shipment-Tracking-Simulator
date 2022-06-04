package shippingUpdate

import jdk.jshell.spi.ExecutionControlProvider
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import shippingUpdate.ShippingInformationParser

internal class ShippingInformationParserTest{

    @Test
    fun parseShipmentUpdateStatus(){
        val shippingInformation: String = "created,S1000,1234567"
        val shippingInformationParser = ShippingInformationParser(shippingInformation)
        assertEquals("created", shippingInformationParser.getShippingUpdateStatus())
    }

    @Test
    fun parseShipmentUpdateId(){
        val shippingInformation: String = "created,S1000,1234567"
        val shippingInformationParser = ShippingInformationParser(shippingInformation)
        assertEquals("S1000", shippingInformationParser.getShippingUpdateId())
    }

    @Test
    fun parseShipmentUpdateIdEmptyString(){
        val shippingInformation: String = "created"
        val shippingInformationParser = ShippingInformationParser(shippingInformation)
        assertThrows<Exception> {
            shippingInformationParser.getShippingUpdateId()
        }
    }

    @Test
    fun parseShipmentUpdateTimestamp(){
        val shippingInformation: String = "created,S1000,1234567"
        val shippingInformationParser = ShippingInformationParser(shippingInformation)
        assertEquals(1234567, shippingInformationParser.getShippingUpdateTimestamp())
    }

    @Test
    fun parseShipmentUpdateTimestampEmptyString(){
        val shippingInformation: String = "created,s1000"
        val shippingInformationParser = ShippingInformationParser(shippingInformation)
        assertThrows<Exception> {
            shippingInformationParser.getShippingUpdateTimestamp()
        }
    }

    @Test
    fun parseTimestampNotANumber(){
        val shippingInformation: String = "created,s1000,notANumber"
        val shippingInformationParser = ShippingInformationParser(shippingInformation)
        assertThrows<Exception> {
            shippingInformationParser.getShippingUpdateTimestamp()
        }
    }

    @Test
    fun parseShipmentTypeHasValue(){
        val shippingInformation: String = "created,S1000,1234567,bulk"
        val shippingInformationParser = ShippingInformationParser(shippingInformation)
        assertEquals("bulk", shippingInformationParser.getShippingUpdateShipmentType())
    }

    @Test
    fun parseShipmentUpdateExpectedDeliveryDate(){
        val shippingInformation: String = "created,S1000,1234567,28357938"
        val shippingInformationParser = ShippingInformationParser(shippingInformation)
        assertEquals(28357938, shippingInformationParser.getShippingUpdateExpectedDeliveryDate())
    }

    @Test
    fun parseExpectedDeliveryDateNotANumber(){
        val shippingInformation: String = "created,s1000,1234567,notANumber"
        val shippingInformationParser = ShippingInformationParser(shippingInformation)
        assertThrows<Exception> {
            shippingInformationParser.getShippingUpdateExpectedDeliveryDate()
        }
    }

    @Test
    fun parseShipmentUpdateExpectedDeliveryDateWithShippingType(){
        val shippingInformation: String = "created,S1000,1234567,express,28357938"
        val shippingInformationParser = ShippingInformationParser(shippingInformation)
        assertEquals("express", shippingInformationParser.getShippingUpdateShipmentType())
        assertEquals(28357938, shippingInformationParser.getShippingUpdateExpectedDeliveryDate())
    }

    @Test
    fun parseUpdateShipmentShippingTypeWithoutExpectedDeliveryDateWith(){
        val shippingInformation: String = "created,S1000,1234567,express"
        val shippingInformationParser = ShippingInformationParser(shippingInformation)
        assertEquals("express", shippingInformationParser.getShippingUpdateShipmentType())
        assertThrows<Exception> {
            shippingInformationParser.getShippingUpdateExpectedDeliveryDate()

        }
    }

    @Test
    fun parseShipmentUpdateExpectedDeliveryDateEmptyString(){
        val shippingInformation: String = "created,s1000,1234567"
        val shippingInformationParser = ShippingInformationParser(shippingInformation)
        assertThrows<Exception> {
            shippingInformationParser.getShippingUpdateExpectedDeliveryDate()
        }
    }

    @Test
    fun parseShipmentUpdateCurrentLocation(){
        val shippingInformation: String = "created,S1000,1234567,California"
        val shippingInformationParser = ShippingInformationParser(shippingInformation)
        assertEquals("California", shippingInformationParser.getShippingUpdateCurrentLocation())
    }

    @Test
    fun parseShipmentUpdateCurrentLocationEmptyString(){
        val shippingInformation: String = "created,s1000,1234567"
        val shippingInformationParser = ShippingInformationParser(shippingInformation)
        assertThrows<Exception> {
            shippingInformationParser.getShippingUpdateCurrentLocation()
        }
    }

    @Test
    fun parseShipmentUpdateNote(){
        val shippingInformation: String = "created,S1000,1234567,note"
        val shippingInformationParser = ShippingInformationParser(shippingInformation)
        assertEquals("note", shippingInformationParser.getShippingUpdateNote())
    }

    @Test
    fun parseShipmentUpdateNoteEmptyString(){
        val shippingInformation: String = "created,s1000,1234567"
        val shippingInformationParser = ShippingInformationParser(shippingInformation)
        assertThrows<Exception> {
            shippingInformationParser.getShippingUpdateNote()
        }
    }
}