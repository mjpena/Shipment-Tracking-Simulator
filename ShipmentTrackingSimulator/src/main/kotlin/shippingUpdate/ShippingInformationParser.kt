package shippingUpdate

class ShippingInformationParser(private val shippingInformation: String) {
    private fun argumentChecker(indexCount: Int): String{
        if (shippingInformation.split(",").size < indexCount + 1){
            throw Exception("Could not parse shipping information. Index out of bounds.")
        }
        return shippingInformation.split(",")[indexCount]
    }

    fun getShippingUpdateStatus(): String{
        return argumentChecker(0)
    }

    fun getShippingUpdateId(): String{
        return argumentChecker(1)
    }

    fun getShippingUpdateTimestamp(): Long{
        return argumentChecker(2).toLong()
    }

    fun getShippingUpdateExpectedDeliveryDate(): Long{
        return argumentChecker(3).toLong()
    }

    fun getShippingUpdateCurrentLocation(): String{
        return argumentChecker(3)
    }

    fun getShippingUpdateNote(): String{
        return argumentChecker(3)
    }

}