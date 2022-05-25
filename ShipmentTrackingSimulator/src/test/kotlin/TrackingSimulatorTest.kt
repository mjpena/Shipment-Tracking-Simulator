import org.junit.jupiter.api.Assertions.*
import shippingUpdate.*
import kotlin.test.Test

internal class TrackingSimulatorTest{
    private val shippingUpdateStrategies = mapOf(
        Pair("created", CreatedShippingUpdateStrategy()),
        Pair("shipped", ShippedShippingUpdateStrategy()),
        Pair("location", LocationShippingUpdateStrategy()),
        Pair("delivered", DeliveredShippingUpdateStrategy()),
        Pair("delayed", DelayedShippingUpdateStrategy()),
        Pair("lost", LostShippingUpdateStrategy()),
        Pair("canceled", CanceledShippingUpdateStrategy()),
        Pair("noteadded", NoteAddedShippingUpdateStrategy()),
    )

    private val shipmentInformationList: List<String>  = listOf(
        "created,S0001,1234534",
        "shipped,S0001,1234534,2983483",
        "location,S0001,1234534,Logan",
        "delayed,S0001,1234534,3829438",
        "lost,S0001,1234534",
        "canceled,S0001,1234534",
        "noteadded,S0001,1234534,note",
        "delivered,S0001,1234534",
    )

    @Test
    fun addShipment(){
        for (shippingInformation in shipmentInformationList) {
            val shippingUpdateStatus = shippingInformation.split(",")[0]
            shippingUpdateStrategies[shippingUpdateStatus]?.updateShipment(shippingInformation) ?: throw Exception("")
            assertTrue(TrackingSimulator.findShipment("S0001")!!.status == shippingUpdateStatus)
        }
    }

    @Test
    fun findNonexistantShipment(){
        assertTrue(TrackingSimulator.findShipment("fakeShipment") == null)
    }
}