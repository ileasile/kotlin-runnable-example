import org.junit.jupiter.api.*
import ru.emkn.kotlin.iterativeFib
import ru.emkn.kotlin.slowFib
import java.lang.IllegalArgumentException
import java.time.Duration
import java.util.stream.IntStream
import java.util.stream.Stream
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class FibonacciTests {
    @Test
    fun `simple test`() {
        assertEquals(2, slowFib(2))
        assertEquals(3, slowFib(3))
        assertEquals(5, slowFib(4))
    }

    @Test
    fun `illegal argument test`() {
        assertFailsWith(IllegalArgumentException::class) {
            slowFib(-2)
        }
    }

    @Test
    fun `timeout test`() {
        val res = assertTimeoutPreemptively(Duration.ofSeconds(5)) {
            iterativeFib(50)
            //slowFib(50)
        }
        assertEquals(20365011074L, res)
    }

    @TestFactory
    fun `multiple test`(): Stream<DynamicTest> {
        val expected: List<Long> = listOf(1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89)
        return IntStream.range(0, 11).mapToObj { n ->
            DynamicTest.dynamicTest("Test fib for $n") {
                assertEquals(expected[n], iterativeFib(n))
            }
        }
    }
}