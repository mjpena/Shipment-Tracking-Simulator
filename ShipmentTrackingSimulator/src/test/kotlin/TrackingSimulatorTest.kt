import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import shippingUpdate.*
import kotlin.test.Test

internal class TrackingSimulatorTest{
    @Test
    fun findNonexistantShipment(){
        assertTrue(TrackingSimulator.findShipment("fakeShipment") == null)
    }

    @Test
    fun runSimulation() = runBlocking {
        TrackingSimulator.runSimulation("C:\\Users\\melan\\Documents\\School\\Summer 2022\\CS 5700\\assignment_2\\test.txt", 0)
    }

    @Test
    fun runSimulation2() = runBlocking {
        TrackingSimulator.runSimulation("C:\\Users\\melan\\Documents\\School\\Summer 2022\\CS 5700\\assignment_2\\test.txt", 0)
    }
}