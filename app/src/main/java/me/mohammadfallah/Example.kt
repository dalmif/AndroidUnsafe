package me.mohammadfallah

class Example {
    var number = 1
    fun test () {
        val unsafe = Unsafe.getUnsafe()
        unsafe.objectFieldOffset(number::class.java.getDeclaredField("number")) // the class have some information and meta in the first 8 byte so the first field starts from 8
        unsafe.getInt(Example(), 8) // equivalent to Example().number because the `number` field starts from the 8th byte so 1 will be returned
    }
}