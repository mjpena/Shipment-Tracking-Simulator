package observer

import TrackingServer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import subject.Shipment

internal class TrackerViewHelperTest{
    private val shippingId: String = "tvht1"
    private val shippingId2: String = "tvht2"
    private val shippingId3: String = "tvht3"
    private val shippingType: String = "standard"
    private val shippingUpdateTimestamp: Long = 2022061001
    private val shippingExpectedDeliveryDate: Long = 2022061405
    private val shipment: Shipment = Shipment.getShipment(shippingId, shippingType, shippingUpdateTimestamp, shippingExpectedDeliveryDate)
    private val shipment2: Shipment = Shipment.getShipment(shippingId2, shippingType, shippingUpdateTimestamp, shippingExpectedDeliveryDate)

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