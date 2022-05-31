package shippingUpdate

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ShippingUpdateTest{
    private val shippingUpdatePreviousStatus: String = "created"
    private val shippingUpdateStatus: String = "shipped"
    private val shippingUpdateTimestamp: Long = 123455
    private val shippingUpdate: ShippingUpdate = ShippingUpdate(shippingUpdatePreviousStatus, shippingUpdateStatus, shippingUpdateTimestamp)

    @Test
    fun getAttributes(){
        assertEquals(shippingUpdate.previousStatus, shippingUpdatePreviousStatus)
        assertEquals(shippingUpdate.newStatus, shippingUpdateStatus)
        assertEquals(shippingUpdate.timeStamp, shippingUpdateTimestamp)
    }

    @Test
    fun negativeLongValue(){
        org.junit.jupiter.api.assertThrows<Exception> {
            ShippingUpdate(shippingUpdatePreviousStatus, shippingUpdateStatus, -1203948)
        }
    }

    @Test
    fun maxLongValue(){
        val shippingUpdate2: ShippingUpdate =ShippingUpdate(shippingUpdatePreviousStatus, shippingUpdateStatus, Long.MAX_VALUE)
        assertEquals(shippingUpdate2.timeStamp, Long.MAX_VALUE)
    }

    @Test
    fun shippingUpdateToString(){
        val shippingUpdateToString = "Shipment went from ${shippingUpdate.previousStatus} to ${shippingUpdate.newStatus} on ${shippingUpdate.timeStamp}."
        assertEquals(shippingUpdate.toString(), shippingUpdateToString)
    }
}
