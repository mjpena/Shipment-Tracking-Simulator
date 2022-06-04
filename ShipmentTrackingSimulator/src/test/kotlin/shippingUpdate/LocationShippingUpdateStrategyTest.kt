package shippingUpdate

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import shippingUpdate.CreatedShippingUpdateStrategy
import shippingUpdate.LocationShippingUpdateStrategy

internal class LocationShippingUpdateStrategyTest{
    @Test
    fun createShippingUpdate(){
        val createdShippingInformation: String = "created,lsust,1234455,standard,94353049"
        val createShippingUpdateStrategy: CreatedShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        createShippingUpdateStrategy.updateShipment(createdShippingInformation)
        val locationShippingInformation: String = "location,lsust,1234455,Logan"
        val locationShippingUpdateStrategy: LocationShippingUpdateStrategy = LocationShippingUpdateStrategy()
        locationShippingUpdateStrategy.updateShipment(locationShippingInformation)
        assertEquals(TrackingServer.findShipment("lsust")!!.status, "location")
        assertEquals(TrackingServer.findShipment("lsust")!!.currentLocation, "Logan")
    }

    @Test
    fun noLocationInfo(){
        val createdShippingInformation: String = "created,lsust2,1234455,standard,94353049"
        val createShippingUpdateStrategy: CreatedShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        createShippingUpdateStrategy.updateShipment(createdShippingInformation)
        val locationShippingInformation: String = "location,lsust2,1234455"
        val locationShippingUpdateStrategy: LocationShippingUpdateStrategy = LocationShippingUpdateStrategy()
        assertThrows<Exception>{
            locationShippingUpdateStrategy.updateShipment(locationShippingInformation)
        }
    }
}