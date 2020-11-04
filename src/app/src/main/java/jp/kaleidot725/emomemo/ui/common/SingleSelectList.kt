package jp.kaleidot725.emomemo.ui.common

class SingleSelectList<T> {
    private val set: MutableSet<T> = mutableSetOf()

    fun add(item: T) {
        set.clear()
        set.add(item)
    }

    fun clear() {
        set.clear()
    }

    fun get(): T {
        return set.first()
    }

    fun getList(): List<T> {
        return set.toList()
    }
}