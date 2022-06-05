import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.engine.*
import shippingUpdate.*
import subject.Shipment

object TrackingServer {
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
        Pair("early", EarlyShippingUpdateStrategy()),
    )

    init {
        embeddedServer(Netty, 8080) {
            routing {
                get("/") {
                    try{
                        val shipmentInformation: String? = call.parameters["shippingInformation"]
                        if (shipmentInformation != null) {
                            val shipmentUpdateStatus = shipmentInformation.split(",")[0]
                            if (shippingUpdateStrategies[shipmentUpdateStatus]?.updateShipment(shipmentInformation) == null){
                                throw Exception("Invalid shipping update status: $shipmentUpdateStatus")
                            }
                            call.respondText("<h1>Shipment Tracking Server</h1><p>Success!</p>", ContentType.Text.Html)
                        }
                    }
                    catch (cause: Throwable){
                        call.respondText("<h1>Shipment Tracking Server</h1><p>Failed. Invalid shipment information.</p>", ContentType.Text.Html)
                    }
                }
            }
        }.start(wait = false)
    }

    fun addShipment(shipment: Shipment){
        if (shipments.contains(shipment) || findShipment(shipment.id) != null) throw Exception("Cannot add shipment. Shipment with ID ${shipment.id} already exists.")
        shipments.add(shipment)
    }

    fun findShipment(id: String): Shipment?{
        return shipments.find { it.id == id }
    }
}