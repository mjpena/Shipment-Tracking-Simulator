import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import subject.Shipment

internal class TrackingServerTest{
    private val shippingID: String = "s10000"
    private val shippingType: String = "bulk"
    private val shippingUpdateTimestamp: Long = 2022061001
    private val shippingExpectedDeliveryDate: Long = 2022061405
    private val shipment: Shipment = Shipment.getShipment(shippingID, shippingType, shippingUpdateTimestamp, shippingExpectedDeliveryDate)

    @Test
    fun addShipmentFindShipment(){
        TrackingServer.addShipment(shipment)
        assertTrue(TrackingServer.findShipment(shippingID) == shipment)
    }

    @Test
    fun addAlreadyPresentShipment(){}

    @Test
    fun findNonexistentShipment() {
        assertTrue(TrackingServer.findShipment("s124353") == null)
    }
}