import kotlinx.coroutines.delay
import shippingUpdate.*
import subject.Shipment
import java.io.File
import java.time.Duration

object TrackingSimulator {
    private val shipments: MutableList<Shipment> = mutableListOf()
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

    fun addShipment(shipment: Shipment){
        shipments.add(shipment)
    }

    fun findShipment(id: String): Shipment?{
        return shipments.find { it.id == id }
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