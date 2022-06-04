package shippingUpdate

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shippingUpdate.CreatedShippingUpdateStrategy
import shippingUpdate.NoteAddedShippingUpdateStrategy

internal class NoteAddedShippingUpdateStrategyTest{
    @Test
    fun createShippingUpdate(){
        val createdShippingInformation: String = "created,nsust,1234455,standard,94353049"
        val createShippingUpdateStrategy: CreatedShippingUpdateStrategy = CreatedShippingUpdateStrategy()
        createShippingUpdateStrategy.updateShipment(createdShippingInformation)
        val noteShippingInformation: String = "note,nsust,1234455,note"
        val noteShippingUpdateStrategy: NoteAddedShippingUpdateStrategy = NoteAddedShippingUpdateStrategy()
        noteShippingUpdateStrategy.updateShipment(noteShippingInformation)
        assertEquals(TrackingServer.findShipment("nsust")!!.status, "note")
        assertTrue(TrackingServer.findShipment("nsust")!!.notes.contains("note") )
    }
}