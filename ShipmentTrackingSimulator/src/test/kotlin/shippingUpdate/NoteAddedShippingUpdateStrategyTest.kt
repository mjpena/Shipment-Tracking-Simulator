package shippingUpdate

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class NoteAddedShippingUpdateStrategyTest{
    @Test
    fun createShippingUpdate(){
        val createdShippingInformation: String = "created,S00001,1234455"
        val createShippingUpdateStrategy: CreatedShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        createShippingUpdateStrategy.updateShipment(createdShippingInformation)
        val noteShippingInformation: String = "note,S00001,1234455,note"
        val noteShippingUpdateStrategy: NoteAddedShippingUpdateStrategy = NoteAddedShippingUpdateStrategy()
        noteShippingUpdateStrategy.updateShipment(noteShippingInformation)
        assertEquals(TrackingServer.findShipment("S00001")!!.status, "note")
        assertTrue(TrackingServer.findShipment("S00001")!!.notes.contains("note") )
    }
}