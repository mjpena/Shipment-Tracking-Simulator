import kotlinx.coroutines.delay
import shippingUpdate.*
import subject.ShipmentFactory
import java.io.File

object TrackingSimulator {
    private val shipmentFactories: MutableList<ShipmentFactory> = mutableListOf()
    private val shippingUpdateStrategies = mapOf(
        Pair("created", CreatedShippingUpdateStrategy()),
        Pair("shipped", ShippedShippingUpdateStrategy()),
        Pair("location", LocationShippingUpdateStrategy()),
        Pair("delivered", DeliveredShippingUpdateStrategy()),
        Pair("delayed", DelayedShippingUpdateStrategy()),
        Pair("lost", LostShippingUpdateStrategy()),
        Pair("canceled", CanceledShippingUpdateStrategy()),
        Pair("noteadded", NoteAddedShippingUpdateStrategy()),
        Pair("early", EarlyShippingUpdateStrategy()),
    )

    fun addShipment(shipmentFactory: ShipmentFactory){
        shipmentFactories.add(shipmentFactory)
    }

    fun findShipment(id: String): ShipmentFactory?{
        return shipmentFactories.find { it.id == id }
    }

    private fun readShipmentInformation(filename: String): List<String>{
        return File(filename).readLines()
    }

    suspend fun runSimulation(filename: String, delay: Long = 1000){
        val shipmentInformationList: List<String> = readShipmentInformation(filename)
        for (shippingInformation in shipmentInformationList){
            delay(delay)
            val shippingUpdateStatus = shippingInformation.split(",")[0]
            // todo: add error catching message
            shippingUpdateStrategies[shippingUpdateStatus]?.updateShipment(shippingInformation) ?: throw Exception("")
        }
    }
}