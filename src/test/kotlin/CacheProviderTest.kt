import cache.CacheProviderDelegation
import dataprovider.Provider
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicInteger
import kotlin.test.assertEquals

class CacheProviderTest {

    private val provider = Provider()


    private val cache = CacheProviderDelegation(provider::fetchUsers)
    private val data by cache

    @BeforeEach
    fun init() {
        provider.loadingCounter = AtomicInteger(0)
    }

    @Test
    fun `cache only one time`() {
        data.forEach { println(it) }
        data.forEach { println(it) }
        data.forEach { println(it) }
        data.forEach { println(it) }

        assertEquals(provider.loadingCounter.get(), 1)
    }

    @Test
    fun `cache and evict`() {
        data.forEach { println(it) }
        data.forEach { println(it) }
        data.forEach { println(it) }
        data.forEach { println(it) }

        cache.evict()

        data.forEach { println(it) }

        assertEquals(provider.loadingCounter.get(), 2)
    }

}