package subject

import TrackingServer
import observer.TrackerViewHelper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import shippingUpdate.CreatedShippingUpdateStrategy
import shippingUpdate.ShippingUpdate
import shippingUpdate.ShippingUpdateStrategy

internal class ShipmentTest{
    private val shipmentId: String = "0001"
    private val shipmentStatus: String = "created"
    private val shipmentNewStatus: String = "shipped"
    private val shipmentCurrentLocation: String = "Logan"
    private val shipmentExpectedDeliveryDate: Long = 193049575904
    private val shipmentNote1: String = "note 1"
    private val shipmentNote2: String = "note 2"
    private val shipment: Shipment = Shipment.getShipment(shipmentId, "standard")
    private val shippingUpdate: ShippingUpdate = ShippingUpdate("N/A", shipmentStatus, 10399890923)

    @Test
    fun getShipmentStandardDefaultStandard(){
        val createdShippingUpdateStrategy: ShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        val thisShippingInformation: String = "created,standardTest,3352353"
        createdShippingUpdateStrategy.updateShipment(thisShippingInformation)
        val thisShipment = TrackingServer.findShipment("standardTest")
        assertTrue(thisShipment is StandardShipment)
        assertTrue(thisShipment!!.shipmentType == "standard")
    }

    @Test
    fun getShipmentStandard(){
        val createdShippingUpdateStrategy: ShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        val thisShippingInformation: String = "created,standardTest2,3352353,standard"
        createdShippingUpdateStrategy.updateShipment(thisShippingInformation)
        val thisShipment = TrackingServer.findShipment("standardTest2")
        assertTrue(thisShipment is StandardShipment)
        assertTrue(thisShipment!!.shipmentType == "standard")
    }

    @Test
    fun getShipmentExpress(){
        val createdShippingUpdateStrategy: ShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        val thisShippingInformation: String = "created,expressTest,3352353,express,12435454"
        createdShippingUpdateStrategy.updateShipment(thisShippingInformation)
        val thisShipment = TrackingServer.findShipment("expressTest")
        assertTrue(thisShipment is ExpressShipment)
        assertTrue(thisShipment!!.shipmentType == "express")
    }

    @Test
    fun getShipmentOvernight(){
        val createdShippingUpdateStrategy: ShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        val thisShippingInformation: String = "created,overnightTest,3352353,overnight,12435454"
        createdShippingUpdateStrategy.updateShipment(thisShippingInformation)
        val thisShipment = TrackingServer.findShipment("overnightTest")
        assertTrue(thisShipment is OvernightShipment)
        assertTrue(thisShipment!!.shipmentType == "overnight")
    }

    @Test
    fun getShipmentBulk(){
        val createdShippingUpdateStrategy: ShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        val thisShippingInformation: String = "created,bulkTest,3352353,bulk,12435454"
        createdShippingUpdateStrategy.updateShipment(thisShippingInformation)
        val thisShipment = TrackingServer.findShipment("bulkTest")
        assertTrue(thisShipment is BulkShipment)
        assertTrue(thisShipment!!.shipmentType == "bulk")
    }

    @Test
    fun getShipmentInvalidType(){
        assertThrows<Exception> {
            val shipment: Shipment = Shipment.getShipment("S1000", "invalid")
        }
    }

    @Test
    fun negativeLongValue(){
        assertThrows<Exception>{
            shipment.expectedDeliveryDate = -1983029384
        }
    }

    @Test
    fun maxLongValue(){
        shipment.expectedDeliveryDate = Long.MAX_VALUE
        assertEquals(shipment.expectedDeliveryDate, Long.MAX_VALUE)
    }

    @Test
    fun addNote(){
        shipment.addNote("note")
    }

    @Test
    fun addUpdate(){
        shipment.addUpdate(shippingUpdate)
    }

    @Test
    fun assignAndGetAttributes(){
        shipment.status = shipmentNewStatus
        assertEquals(shipment.status, shipmentNewStatus)
        shipment.currentLocation = shipmentCurrentLocation
        assertEquals(shipment.currentLocation, shipmentCurrentLocation)
        shipment.expectedDeliveryDate = shipmentExpectedDeliveryDate
        assertEquals(shipment.expectedDeliveryDate, shipmentExpectedDeliveryDate)
        shipment.addNote(shipmentNote1)
        shipment.addNote(shipmentNote2)
        assertEquals(shipment.notes[0], shipmentNote1)
        assertEquals(shipment.notes[1], shipmentNote2)
        shipment.addUpdate(shippingUpdate)
        assertEquals(shipment.updateHistory[0], shippingUpdate)
    }

    @Test
    fun registerRemoveObserver(){
        val trackerViewHelper: TrackerViewHelper = TrackerViewHelper(shipmentId)
        val trackerViewHelper2: TrackerViewHelper = TrackerViewHelper(shipmentId)
        val shipment: Shipment = Shipment.getShipment(shipmentId, "standard")
        shipment.registerObserver(trackerViewHelper)
        shipment.registerObserver(trackerViewHelper2)
        assertTrue(shipment.observers.contains(trackerViewHelper))
        assertTrue(shipment.observers.contains(trackerViewHelper2))
        shipment.removeObserver(trackerViewHelper)
        assertFalse(shipment.observers.contains(trackerViewHelper))
        shipment.removeObserver(trackerViewHelper2)
        assertFalse(shipment.observers.contains(trackerViewHelper2))
    }

    @Test
    fun addAlreadyRegisteredObserver(){
        val trackerViewHelper: TrackerViewHelper = TrackerViewHelper(shipmentId)
        shipment.registerObserver(trackerViewHelper)
        shipment.registerObserver(trackerViewHelper)
        assertTrue(shipment.observers.size == 1)
    }

    @Test
    fun removeNonRegisteredObserver(){
        shipment.observers.clear()
        assertTrue(shipment.observers.size == 0)
        val trackerViewHelper: TrackerViewHelper = TrackerViewHelper(shipmentId)
        shipment.removeObserver(trackerViewHelper)
    }
}