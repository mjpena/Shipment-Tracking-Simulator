package shippingUpdate

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class LocationShippingUpdateStrategyTest{
    @Test
    fun createShippingUpdate(){
        val createdShippingInformation: String = "created,S00001,1234455"
        val createShippingUpdateStrategy: CreatedShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        createShippingUpdateStrategy.updateShipment(createdShippingInformation)
        val locationShippingInformation: String = "location,S00001,1234455,Logan"
        val locationShippingUpdateStrategy: LocationShippingUpdateStrategy = LocationShippingUpdateStrategy()
        locationShippingUpdateStrategy.updateShipment(locationShippingInformation)
        assertEquals(TrackingSimulator.findShipment("S00001")!!.status, "location")
        assertEquals(TrackingSimulator.findShipment("S00001")!!.currentLocation, "Logan")
    }

    @Test
    fun noLocationInfo(){
        val createdShippingInformation: String = "created,S00001,1234455"
        val createShippingUpdateStrategy: CreatedShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        createShippingUpdateStrategy.updateShipment(createdShippingInformation)
        val locationShippingInformation: String = "location,S00001,1234455"
        val locationShippingUpdateStrategy: LocationShippingUpdateStrategy = LocationShippingUpdateStrategy()
        //todo: check for not enough info shipping info
        locationShippingUpdateStrategy.updateShipment(locationShippingInformation)
    }
}