package shippingUpdate

class ShippingInformationParser(private val shippingInformation: String) {
    private fun parserChecker(indexCount: Int): String{
        if (shippingInformation.split(",").size < indexCount + 1){
            throw Exception("Could not parse shipping information. Index out of bounds.")
        }
        return shippingInformation.split(",")[indexCount]
    }

    fun getShippingUpdateStatus(): String{
        return parserChecker(0)
    }

    fun getShippingUpdateId(): String{
        return parserChecker(1)
    }

    fun getShippingUpdateTimestamp(): Long{
        return parserChecker(2).toLong()
    }

    fun getShippingUpdateExpectedDeliveryDate(): Long{
        return parserChecker(3).toLong()
    }

    fun getShippingUpdateCurrentLocation(): String{
        return parserChecker(3)
    }

    fun getShippingUpdateNote(): String{
        return parserChecker(3)
    }

}