package dataprovider

import java.util.concurrent.atomic.AtomicInteger
import java.util.logging.Logger

data class User(
    val id: Long,
    val name: String,
    val age: Int
)

class Provider {

    private var logger: Logger = Logger.getLogger(this.javaClass.name)

    private var users: MutableList<User> = mutableListOf()

    var loadingCounter = AtomicInteger(0)

    fun fetchUsers(): List<User> {
        loadingCounter.addAndGet(1)
        logger.info("Fetching user list")
        users.add(User(1, "user-1", 28))
        users.add(User(1, "user-2", 30))
        users.add(User(1, "user-3", 24))
        return users
    }

}