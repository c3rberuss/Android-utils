package com.c3rberuss.androidutils

suspend fun <T> T.insertOrUpdate(
    areTheSame: (item: T) -> Boolean,
    local: T?,
    insert: suspend (item: T) -> Unit,
    update: suspend (item: T) -> Unit
) {
    if (local == null) {
        insert(this)
        return
    }

    if (!areTheSame(this)) {
        update(this)
    }

}

suspend fun <T> List<T>.deleteDiff(
    items: List<T>,
    areTheSame: (item1: T, item2: T) -> Boolean,
    delete: suspend (item: T) -> Unit
) {
    this.filterNot { item1 -> items.find { item2 -> areTheSame(item1, item2) } != null }.forEach {
        delete(it)
    }
}

fun <T> MutableList<T>.addIfNotExists(value: T) {
    if (!this.contains(value)) {
        this.add(value)
    }
}

fun <T> MutableList<T>.addAllIfNotExists(newItems: List<T>) {
    newItems.forEach { newItem ->
        this.addIfNotExists(newItem)
    }
}