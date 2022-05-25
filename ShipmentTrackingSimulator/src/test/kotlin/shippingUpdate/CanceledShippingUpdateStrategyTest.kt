package shippingUpdate

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CanceledShippingUpdateStrategyTest{
    val shippingInformation = "created,1934830,2902202093"

    @Test
    fun getShippingUpdate(){
        val canceledShippingUpdateStrategy: CanceledShippingUpdateStrategy = CanceledShippingUpdateStrategy()
        assertEquals(canceledShippingUpdateStrategy.getShippingUpdateId(shippingInformation), "created")
    }
}