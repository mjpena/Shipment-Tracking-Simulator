import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.engine.*
import shippingUpdate.*
import subject.Shipment

object TrackingServer {
    private val shipmentFactories: MutableList<Shipment> = mutableListOf()
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
                    call.respondText("<h1>Shipment Tracking Server</h1>", ContentType.Text.Html)

                    val shippingInformation: String? = call.parameters["shippingInformation"]
                    if (shippingInformation != null) {
                        println(shippingInformation)
                        call.respondText("Processing request: $shippingInformation", ContentType.Text.Html)
                        val shippingUpdateStatus = shippingInformation.split(",")[0]
                        shippingUpdateStrategies[shippingUpdateStatus]?.updateShipment(shippingInformation) ?: throw Exception("Invalid shipping update status: $shippingUpdateStatus")
                    }
                }
            }
        }.start(wait = false)
    }

    fun addShipment(shipment: Shipment){
        shipmentFactories.add(shipment)
    }

    fun findShipment(id: String): Shipment?{
        return shipmentFactories.find { it.id == id }
    }
}