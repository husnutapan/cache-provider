# cache-provider
This application represent to delegate method and it works as caching mechanism also it will reduce the multiple time of the fetch data from external services


 
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
