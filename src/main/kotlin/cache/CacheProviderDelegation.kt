package cache

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


class CacheProviderDelegation<out T>(val dataLoader: () -> T) : ReadOnlyProperty<Any, T> {

    private var value: CachedValue<T> = CachedValue.Invalid

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return when (val cachedValue = this.value) {
            CachedValue.Invalid -> dataLoader().also { this.value = CachedValue.Value(it) }
            is CachedValue.Value -> cachedValue.value
        }
    }

    fun evict() {
        value = CachedValue.Invalid
    }

    private sealed class CachedValue<out T> {
        object Invalid : CachedValue<Nothing>()
        class Value<out T>(val value: T) : CachedValue<T>()
    }
}