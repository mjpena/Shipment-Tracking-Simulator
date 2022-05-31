package observer

import TrackingServer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import subject.Shipment

internal class TrackerViewHelperTest{
    private val shippingId: String = "s10001"
    private val shippingId2: String = "s10002"
    private val shippingId3: String = "s10003"
    private val shippingType: String = "express"
    private val shipment: Shipment = Shipment.getShipment(shippingId, shippingType)
    private val shipment2: Shipment = Shipment.getShipment(shippingId2, shippingType)

    @Test
    fun trackShipment(){
        TrackingServer.addShipment(shipment)
        val trackerViewHelper: TrackerViewHelper = TrackerViewHelper(shippingId)
        assertTrue(trackerViewHelper.shipment == shipment)
    }

    @Test
    fun trackNonexistentShipment(){
        val trackerViewHelper: TrackerViewHelper = TrackerViewHelper(shippingId3)
        assertTrue(trackerViewHelper.shipment == null)
    }

    @Test
    fun stopTrackingShipment(){
        TrackingServer.addShipment(shipment2)
        val trackerViewHelper: TrackerViewHelper = TrackerViewHelper(shippingId2)
        assertTrue(trackerViewHelper.shipment == shipment2)
        trackerViewHelper.stopTracking()
    }

    @Test
    fun stopTrackingNonexistentShipment(){
        val trackerViewHelper: TrackerViewHelper = TrackerViewHelper(shippingId3)
        assertTrue(trackerViewHelper.shipment == null)
        trackerViewHelper.stopTracking()
    }
}