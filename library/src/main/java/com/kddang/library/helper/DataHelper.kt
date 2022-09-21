package com.kddang.library.helper


interface DataHelper<T> {
    fun addAll(list: List<T>): Boolean

    fun addAll(position: Int, list: List<T>): Boolean

    fun add(data: T)

    fun add(position: Int, data: T)

    fun clear()

    fun contains(data: T): Boolean

    fun getData(index: Int): T

    fun modify(oldData: T, newData: T)

    fun modify(index: Int, newData: T)

    fun remove(data: T): Boolean

    fun remove(index: Int)

}