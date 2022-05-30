package subject

import observer.TrackerViewHelper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import shippingUpdate.ShippingUpdate

internal class ShipmentFactoryTest{
    private val shipmentId: String = "0001"
    private val shipmentStatus: String = "created"
    private val shipmentNewStatus: String = "shipped"
    private val shipmentCurrentLocation: String = "Logan"
    private val shipmentExpectedDeliveryDate: Long = 193049575904
    private val shipmentNote1: String = "note 1"
    private val shipmentNote2: String = "note 2"
    private val shipmentFactory: ShipmentFactory = ShipmentFactory.getShipment("standard", shipmentId)
    private val shippingUpdate: ShippingUpdate = ShippingUpdate("N/A", shipmentStatus, 10399890923)

    @Test
    fun getShipmentStandard(){
        val shipment: ShipmentFactory = ShipmentFactory.getShipment("standard", "S1000")
        assertTrue(shipment is StandardShipment)
    }

    @Test
    fun getShipmentExpress(){
        val shipment: ShipmentFactory = ShipmentFactory.getShipment("express", "S1000")
        assertTrue(shipment is ExpressShipment)
    }

    @Test
    fun getShipmentOvernight(){
        val shipment: ShipmentFactory = ShipmentFactory.getShipment("overnight", "S1000")
        assertTrue(shipment is OvernightShipment)
    }

    @Test
    fun getShipmentBulk(){
        val shipment: ShipmentFactory = ShipmentFactory.getShipment("bulk", "S1000")
        assertTrue(shipment is BulkShipment)
    }

    @Test
    fun getShipmentInvalidType(){
        assertThrows<Exception> {
            val shipment: ShipmentFactory = ShipmentFactory.getShipment("invalid", "S1000")
        }
    }

    @Test
    fun negativeLongValue(){
        assertThrows<Exception>{
            shipmentFactory.expectedDeliveryDate = -1983029384
        }
    }

    @Test
    fun maxLongValue(){
        shipmentFactory.expectedDeliveryDate = Long.MAX_VALUE
        assertEquals(shipmentFactory.expectedDeliveryDate, Long.MAX_VALUE)
    }

    @Test
    fun addNote(){
        shipmentFactory.addNote("note")
    }

    @Test
    fun addUpdate(){
        shipmentFactory.addUpdate(shippingUpdate)
    }

    @Test
    fun assignAndGetAttributes(){
        shipmentFactory.status = shipmentNewStatus
        assertEquals(shipmentFactory.status, shipmentNewStatus)
        shipmentFactory.currentLocation = shipmentCurrentLocation
        assertEquals(shipmentFactory.currentLocation, shipmentCurrentLocation)
        shipmentFactory.expectedDeliveryDate = shipmentExpectedDeliveryDate
        assertEquals(shipmentFactory.expectedDeliveryDate, shipmentExpectedDeliveryDate)
        shipmentFactory.addNote(shipmentNote1)
        shipmentFactory.addNote(shipmentNote2)
        assertEquals(shipmentFactory.notes[0], shipmentNote1)
        assertEquals(shipmentFactory.notes[1], shipmentNote2)
        shipmentFactory.addUpdate(shippingUpdate)
        assertEquals(shipmentFactory.updateHistory[0], shippingUpdate)
    }

    @Test
    fun registerRemoveObserver(){
        val trackerViewHelper: TrackerViewHelper = TrackerViewHelper(shipmentId)
        val trackerViewHelper2: TrackerViewHelper = TrackerViewHelper(shipmentId)
        val shipmentFactory: ShipmentFactory = ShipmentFactory.getShipment("standard", shipmentId)
        shipmentFactory.registerObserver(trackerViewHelper)
        shipmentFactory.registerObserver(trackerViewHelper2)
        assertTrue(shipmentFactory.observers.contains(trackerViewHelper))
        shipmentFactory.removeObserver(trackerViewHelper)
        assertFalse(shipmentFactory.observers.contains(trackerViewHelper))
        shipmentFactory.removeObserver(trackerViewHelper2)
        assertFalse(shipmentFactory.observers.contains(trackerViewHelper2))
    }

    @Test
    fun addAlreadyRegisteredObserver(){
        val trackerViewHelper: TrackerViewHelper = TrackerViewHelper(shipmentId)
        shipmentFactory.registerObserver(trackerViewHelper)
        shipmentFactory.registerObserver(trackerViewHelper)
        assertTrue(shipmentFactory.observers.size == 1)
    }

    @Test
    fun removeNonRegisteredObserver(){
        shipmentFactory.observers.clear()
        assertTrue(shipmentFactory.observers.size == 0)
        val trackerViewHelper: TrackerViewHelper = TrackerViewHelper(shipmentId)
        shipmentFactory.removeObserver(trackerViewHelper)
    }
}