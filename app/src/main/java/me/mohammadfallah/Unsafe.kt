package me.mohammadfallah

import java.lang.reflect.Field


class Unsafe(private val realClass : Class<*>) {

    private val realObject : Any

    init {
        try {
            val field: Field = try {
                realClass.getDeclaredField("theUnsafe")
            } catch (exception: NoSuchFieldException) {
                realClass.getDeclaredField("THE_ONE")
            }
            field.isAccessible = true
            realObject = field.get(null) ?: throw Exception("the field 'theUnsafe' or 'THE_ONE' not found")
        } catch (exception : Exception) {
            throw Error("There is no any access to Unsafe");
        }
    }
    companion object {
        private lateinit var theUnsafe : Unsafe;

        fun getUnsafe () : Unsafe {
            if (::theUnsafe.isInitialized) {
                return theUnsafe;
            }
            theUnsafe = Unsafe(Class.forName("sun.misc.Unsafe"))
            return theUnsafe;
        }
    }

    /**
     * Gets the raw byte offset from the start of an object's memory to
     * the memory used to store the indicated instance field.
     *
     * @param field non-null; the field in question, which must be an
     * instance field
     * @return the offset to the field
     */
    fun objectFieldOffset(field: Field): Long {
        val method = realClass.getDeclaredMethod("objectFieldOffset", Field::class.java);
        return method.invoke(realObject, field) as Long
    }

    /**
     * Gets the offset from the start of an array object's memory to
     * the memory used to store its initial (zeroeth) element.
     *
     * @param clazz non-null; class in question; must be an array class
     * @return the offset to the initial element
     */
    fun arrayBaseOffset(clazz: Class<*>): Int {
        val method = realClass.getDeclaredMethod("arrayBaseOffset", Class::class.java);
        return method.invoke(realObject, clazz) as Int
    }

    /**
     * Gets the size of each element of the given array class.
     *
     * @param clazz non-null; class in question; must be an array class
     * @return &gt; 0; the size of each element of the array
     */
    fun arrayIndexScale(clazz: Class<*>): Int {
        val method = realClass.getDeclaredMethod("arrayIndexScale", Class::class.java);
        return method.invoke(realObject, clazz) as Int
    }
    /**
     * Performs a compare-and-set operation on an `int`
     * field within the given object.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within `obj`
     * @param expectedValue expected value of the field
     * @param newValue new value to store in the field if the contents are
     * as expected
     * @return `true` if the new value was in fact stored, and
     * `false` if not
     */
    fun compareAndSwapInt(
        obj: Any?, offset: Long,
        expectedValue: Int, newValue: Int
    ): Boolean {
        val method = realClass.getDeclaredMethod("compareAndSwapInt", Object::class.java,Long::class.java,Int::class.java,Int::class.java);
        return method.invoke(realObject, obj,offset,expectedValue,newValue) as Boolean
    }

    /**
     * Performs a compare-and-set operation on a `long`
     * field within the given object.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within `obj`
     * @param expectedValue expected value of the field
     * @param newValue new value to store in the field if the contents are
     * as expected
     * @return `true` if the new value was in fact stored, and
     * `false` if not
     */
    fun compareAndSwapLong(
        obj: Any?, offset: Long,
        expectedValue: Long, newValue: Long
    ): Boolean {
        val method = realClass.getDeclaredMethod("compareAndSwapLong", Object::class.java,Long::class.java,Long::class.java,Long::class.java);
        return method.invoke(realObject, obj,offset,expectedValue,newValue) as Boolean
    }

    /**
     * Performs a compare-and-set operation on an `Object`
     * field (that is, a reference field) within the given object.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within `obj`
     * @param expectedValue expected value of the field
     * @param newValue new value to store in the field if the contents are
     * as expected
     * @return `true` if the new value was in fact stored, and
     * `false` if not
     */
    fun compareAndSwapObject(
        obj: Any?, offset: Long,
        expectedValue: Any?, newValue: Any?
    ): Boolean {
        val method = realClass.getDeclaredMethod("compareAndSwapObject", Object::class.java,Long::class.java,Object::class.java,Object::class.java);
        return method.invoke(realObject, obj,offset,expectedValue,newValue) as Boolean
    }

    /**
     * Gets an `int` field from the given object,
     * using `volatile` semantics.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within `obj`
     * @return the retrieved value
     */
    fun getIntVolatile(obj: Any?, offset: Long): Int {
        val method = realClass.getDeclaredMethod("getIntVolatile", Object::class.java,Long::class.java);
        return method.invoke(realObject, obj,offset) as Int
    }

    /**
     * Stores an `int` field into the given object,
     * using `volatile` semantics.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within `obj`
     * @param newValue the value to store
     */
    fun putIntVolatile(obj: Any?, offset: Long, newValue: Int) {
        val method = realClass.getDeclaredMethod("putIntVolatile", Object::class.java,Long::class.java,Int::class.java);
        method.invoke(realObject, obj,offset,newValue)
    }

    /**
     * Gets a `long` field from the given object,
     * using `volatile` semantics.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within `obj`
     * @return the retrieved value
     */
    fun getLongVolatile(obj: Any?, offset: Long): Long {
        val method = realClass.getDeclaredMethod("getLongVolatile", Object::class.java,Long::class.java);
        return method.invoke(realObject, obj,offset) as Long
    }

    /**
     * Stores a `long` field into the given object,
     * using `volatile` semantics.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within `obj`
     * @param newValue the value to store
     */
    fun putLongVolatile(obj: Any?, offset: Long, newValue: Long) {
        val method = realClass.getDeclaredMethod("putLongVolatile", Object::class.java,Long::class.java,Long::class.java);
        method.invoke(realObject, obj,offset,newValue)
    }

    /**
     * Gets an `Object` field from the given object,
     * using `volatile` semantics.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within `obj`
     * @return the retrieved value
     */
    fun getObjectVolatile(obj: Any?, offset: Long): Any? {
        val method = realClass.getDeclaredMethod("getObjectVolatile", Object::class.java,Long::class.java);
        return method.invoke(realObject, obj,offset) as Any?
    }

    /**
     * Stores an `Object` field into the given object,
     * using `volatile` semantics.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within `obj`
     * @param newValue the value to store
     */
    fun putObjectVolatile(
        obj: Any?, offset: Long,
        newValue: Any?
    ) {
        val method = realClass.getDeclaredMethod("putObjectVolatile", Object::class.java,Long::class.java,Object::class.java);
        method.invoke(realObject, obj,offset,newValue)
    }

    /**
     * Gets an `int` field from the given object.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within `obj`
     * @return the retrieved value
     */
    fun getInt(obj: Any?, offset: Long): Int {
        val method = realClass.getDeclaredMethod("getInt", Object::class.java, Long::class.java);
        return method.invoke(realObject, obj, offset) as Int
    }

    /**
     * Stores an `int` field into the given object.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within `obj`
     * @param newValue the value to store
     */
    fun putInt(obj: Any?, offset: Long, newValue: Int) {
        val method = realClass.getDeclaredMethod("putInt", Object::class.java,Long::class.java,Int::class.java);
        method.invoke(realObject, obj,offset,newValue)
    }

    /**
     * Lazy set an int field.
     */
    fun putOrderedInt(obj: Any?, offset: Long, newValue: Int) {
        val method = realClass.getDeclaredMethod("putOrderedInt", Object::class.java,Long::class.java,Int::class.java);
        method.invoke(realObject, obj,offset,newValue)
    }

    /**
     * Gets a `long` field from the given object.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within `obj`
     * @return the retrieved value
     */
    fun getLong(obj: Any?, offset: Long): Long {
        val method = realClass.getDeclaredMethod("getLong", Object::class.java,Long::class.java);
        return method.invoke(realObject, obj,offset) as Long
    }

    /**
     * Stores a `long` field into the given object.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within `obj`
     * @param newValue the value to store
     */
    fun putLong(obj: Any?, offset: Long, newValue: Long) {
        val method = realClass.getDeclaredMethod("putLong", Object::class.java,Long::class.java,Long::class.java);
        method.invoke(realObject, obj,offset,newValue)
    }

    /**
     * Lazy set a long field.
     */
    fun putOrderedLong(obj: Any?, offset: Long, newValue: Long) {
        val method = realClass.getDeclaredMethod("putOrderedLong", Object::class.java,Long::class.java,Long::class.java);
        method.invoke(realObject, obj,offset,newValue)
    }

    /**
     * Gets an `Object` field from the given object.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within `obj`
     * @return the retrieved value
     */
    fun getObject(obj: Any?, offset: Long): Any? {
        val method = realClass.getDeclaredMethod("getObject", Object::class.java,Long::class.java);
        return method.invoke(realObject, obj,offset)
    }

    /**
     * Stores an `Object` field into the given object.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within `obj`
     * @param newValue the value to store
     */
    fun putObject(obj: Any?, offset: Long, newValue: Any?) {
        val method = realClass.getDeclaredMethod("putObject", Object::class.java,Long::class.java,Object::class.java);
        method.invoke(realObject, obj,offset,newValue)
    }

    /**
     * Lazy set an object field.
     */
    fun putOrderedObject(
        obj: Any?, offset: Long,
        newValue: Any?
    ) {
        val method = realClass.getDeclaredMethod("putOrderedObject", Object::class.java,Long::class.java,Object::class.java);
        method.invoke(realObject, obj,offset,newValue)
    }

    fun getBoolean(obj: Any?, offset: Long): Boolean {
        val method = realClass.getDeclaredMethod("getBoolean", Object::class.java,Long::class.java);
        return method.invoke(realObject, obj,offset) as Boolean
    }
    fun putBoolean(obj: Any?, offset: Long, newValue: Boolean) {
        val method = realClass.getDeclaredMethod("putBoolean", Object::class.java,Long::class.java,Boolean::class.java);
        method.invoke(realObject, obj,offset,newValue)
    }
    fun getByte(obj: Any?, offset: Long): Byte {
        val method = realClass.getDeclaredMethod("getByte", Object::class.java,Long::class.java);
        return method.invoke(realObject, obj,offset) as Byte
    }
    fun putByte(obj: Any?, offset: Long, newValue: Byte) {
        val method = realClass.getDeclaredMethod("putByte", Object::class.java,Long::class.java,Byte::class.java);
        method.invoke(realObject, obj,offset,newValue)
    }
    fun getChar(obj: Any?, offset: Long): Char {
        val method = realClass.getDeclaredMethod("getChar", Object::class.java,Long::class.java);
        return method.invoke(realObject, obj,offset) as Char
    }
    fun putChar(obj: Any?, offset: Long, newValue: Char) {
        val method = realClass.getDeclaredMethod("putChar", Object::class.java,Long::class.java,Char::class.java);
        method.invoke(realObject, obj,offset,newValue)
    }
    fun getShort(obj: Any?, offset: Long): Short {
        val method = realClass.getDeclaredMethod("getShort", Object::class.java,Long::class.java);
        return method.invoke(realObject, obj,offset) as Short
    }
    fun putShort(obj: Any?, offset: Long, newValue: Short) {
        val method = realClass.getDeclaredMethod("putShort", Object::class.java,Long::class.java,Short::class.java);
        method.invoke(realObject, obj,offset,newValue)
    }
    fun getFloat(obj: Any?, offset: Long): Float {
        val method = realClass.getDeclaredMethod("getFloat", Object::class.java,Long::class.java);
        return method.invoke(realObject, obj,offset) as Float
    }
    fun putFloat(obj: Any?, offset: Long, newValue: Float) {
        val method = realClass.getDeclaredMethod("putFloat", Object::class.java,Long::class.java,Float::class.java);
        method.invoke(realObject, obj,offset,newValue)
    }
    fun getDouble(obj: Any?, offset: Long): Double {
        val method = realClass.getDeclaredMethod("getDouble", Object::class.java,Long::class.java);
        return method.invoke(realObject, obj,offset) as Double
    }
    fun putDouble(obj: Any?, offset: Long, newValue: Double) {
        val method = realClass.getDeclaredMethod("putDouble", Object::class.java,Long::class.java,Double::class.java);
        method.invoke(realObject, obj,offset,newValue)
    }

    /**
     * Parks the calling thread for the specified amount of time,
     * unless the "permit" for the thread is already available (due to
     * a previous call to [.unpark]. This method may also return
     * spuriously (that is, without the thread being told to unpark
     * and without the indicated amount of time elapsing).
     *
     *
     * See [java.util.concurrent.locks.LockSupport] for more
     * in-depth information of the behavior of this method.
     *
     * @param absolute whether the given time value is absolute
     * milliseconds-since-the-epoch (`true`) or relative
     * nanoseconds-from-now (`false`)
     * @param time the (absolute millis or relative nanos) time value
     */
    fun park(absolute: Boolean, time: Long) {
        val method = realClass.getDeclaredMethod("putDouble", Boolean::class.java,Long::class.java);
        method.invoke(realObject, absolute,time)
    }

    /**
     * Unparks the given object, which must be a [Thread].
     *
     *
     * See [java.util.concurrent.locks.LockSupport] for more
     * in-depth information of the behavior of this method.
     *
     * @param obj non-null; the object to unpark
     */
    fun unpark(obj: Any?) {
        val method = realClass.getDeclaredMethod("putDouble", Boolean::class.java);
        method.invoke(realObject, obj)
    }

    /**
     * Allocates an instance of the given class without running the constructor.
     * The class' <clinit> will be run, if necessary.
    </clinit> */
    fun allocateInstance(c: Class<*>?): Any? {
        val method = realClass.getDeclaredMethod("allocateInstance", Class::class.java);
        return method.invoke(realObject, c)
    }
    fun addressSize(): Int {
        val method = realClass.getDeclaredMethod("addressSize");
        return method.invoke(realObject) as Int
    }
    fun pageSize(): Int {
        val method = realClass.getDeclaredMethod("pageSize");
        return method.invoke(realObject)  as Int
    }
    fun allocateMemory(bytes: Long): Long {
        val method = realClass.getDeclaredMethod("allocateMemory", Long::class.java);
        return method.invoke(realObject, bytes) as Long
    }
    fun freeMemory(address: Long) {
        val method = realClass.getDeclaredMethod("freeMemory", Long::class.java);
        method.invoke(realObject, address)
    }
    fun setMemory(address: Long, bytes: Long, value: Byte) {
        val method = realClass.getDeclaredMethod("setMemory", Long::class.java,Long::class.java,Byte::class.java);
        method.invoke(realObject, address,bytes,value)
    }
    fun getByte(address: Long): Byte {
        val method = realClass.getDeclaredMethod("getByte", Long::class.java);
        return method.invoke(realObject, address) as Byte
    }
    fun putByte(address: Long, x: Byte) {
        val method = realClass.getDeclaredMethod("getByte", Long::class.java, Byte::class.java);
        method.invoke(realObject, address, x)
    }
    fun getShort(address: Long): Short {
        val method = realClass.getDeclaredMethod("getShort", Long::class.java);
        return method.invoke(realObject, address) as Short
    }
    fun putShort(address: Long, x: Short) {
        val method = realClass.getDeclaredMethod("putShort", Long::class.java, Short::class.java);
        method.invoke(realObject, address, x)
    }
    fun getChar(address: Long): Char {
        val method = realClass.getDeclaredMethod("getChar", Long::class.java);
        return method.invoke(realObject, address) as Char
    }
    fun putChar(address: Long, x: Char) {
        val method = realClass.getDeclaredMethod("putChar", Long::class.java, Char::class.java);
        method.invoke(realObject, address, x)
    }
    fun getInt(address: Long): Int {
        val method = realClass.getDeclaredMethod("getInt", Long::class.java);
        return method.invoke(realObject, address) as Int
    }
    fun putInt(address: Long, x: Int) {
        val method = realClass.getDeclaredMethod("putInt", Long::class.java, Int::class.java);
        method.invoke(realObject, address, x)
    }
    fun getLong(address: Long): Long {
        val method = realClass.getDeclaredMethod("getLong", Long::class.java);
        return method.invoke(realObject, address) as Long
    }
    fun putLong(address: Long, x: Long) {
        val method = realClass.getDeclaredMethod("putLong", Long::class.java, Long::class.java);
        method.invoke(realObject, address, x)
    }
    fun getFloat(address: Long): Float {
        val method = realClass.getDeclaredMethod("getFloat", Long::class.java);
        return method.invoke(realObject, address) as Float
    }
    fun putFloat(address: Long, x: Float) {
        val method = realClass.getDeclaredMethod("putFloat", Long::class.java, Float::class.java);
        method.invoke(realObject, address, x)
    }
    fun getDouble(address: Long): Double {
        val method = realClass.getDeclaredMethod("getDouble", Long::class.java);
        return method.invoke(realObject, address) as Double
    }
    fun putDouble(address: Long, x: Double) {
        val method = realClass.getDeclaredMethod("putDouble", Long::class.java, Double::class.java);
        method.invoke(realObject, address, x)
    }
    fun copyMemoryToPrimitiveArray(
        srcAddr: Long,
        dst: Any?, dstOffset: Long, bytes: Long
    ) {
        val method = realClass.getDeclaredMethod("copyMemoryToPrimitiveArray", Long::class.java, Object::class.java,Long::class.java,Long::class.java);
        method.invoke(realObject, srcAddr, dst, dstOffset,bytes)
    }

    fun copyMemoryFromPrimitiveArray(
        src: Any?, srcOffset: Long,
        dstAddr: Long, bytes: Long
    ) {
        val method = realClass.getDeclaredMethod("copyMemoryFromPrimitiveArray", Object::class.java, Long::class.java,Long::class.java,Long::class.java);
        method.invoke(realObject, src, srcOffset, dstAddr,bytes)
    }

    fun copyMemory(srcAddr: Long, dstAddr: Long, bytes: Long) {
        val method = realClass.getDeclaredMethod("copyMemory", Long::class.java, Long::class.java,Long::class.java);
        method.invoke(realObject, srcAddr, dstAddr, bytes)
    }
    // The following contain CAS-based Java implementations used on
    // platforms not supporting native instructions
    // The following contain CAS-based Java implementations used on
    // platforms not supporting native instructions
    /**
     * Atomically adds the given value to the current value of a field
     * or array element within the given object `o`
     * at the given `offset`.
     *
     * @param o object/array to update the field/element in
     * @param offset field/element offset
     * @param delta the value to add
     * @return the previous value
     * @since 1.8
     */
    // @HotSpotIntrinsicCandidate
    fun getAndAddInt(o: Any?, offset: Long, delta: Int): Int {
        val method = realClass.getDeclaredMethod("getAndAddInt", Object::class.java,Long::class.java,Int::class.java);
        return method.invoke(o, offset, delta) as Int
    }

    /**
     * Atomically adds the given value to the current value of a field
     * or array element within the given object `o`
     * at the given `offset`.
     *
     * @param o object/array to update the field/element in
     * @param offset field/element offset
     * @param delta the value to add
     * @return the previous value
     * @since 1.8
     */
    // @HotSpotIntrinsicCandidate
    fun getAndAddLong(o: Any?, offset: Long, delta: Long): Long {
        val method = realClass.getDeclaredMethod("getAndAddLong", Object::class.java,Long::class.java,Long::class.java);
        return method.invoke(o, offset, delta) as Long
    }

    /**
     * Atomically exchanges the given value with the current value of
     * a field or array element within the given object `o`
     * at the given `offset`.
     *
     * @param o object/array to update the field/element in
     * @param offset field/element offset
     * @param newValue new value
     * @return the previous value
     * @since 1.8
     */
    // @HotSpotIntrinsicCandidate
    fun getAndSetInt(o: Any?, offset: Long, newValue: Int): Int {
        val method = realClass.getDeclaredMethod("getAndSetInt", Object::class.java,Long::class.java,Int::class.java);
        return method.invoke(o, offset, newValue) as Int
    }

    /**
     * Atomically exchanges the given value with the current value of
     * a field or array element within the given object `o`
     * at the given `offset`.
     *
     * @param o object/array to update the field/element in
     * @param offset field/element offset
     * @param newValue new value
     * @return the previous value
     * @since 1.8
     */
    // @HotSpotIntrinsicCandidate
    fun getAndSetLong(o: Any?, offset: Long, newValue: Long): Long {
        val method = realClass.getDeclaredMethod("getAndSetLong", Object::class.java,Long::class.java,Long::class.java);
        return method.invoke(o, offset, newValue) as Long
    }

    /**
     * Atomically exchanges the given reference value with the current
     * reference value of a field or array element within the given
     * object `o` at the given `offset`.
     *
     * @param o object/array to update the field/element in
     * @param offset field/element offset
     * @param newValue new value
     * @return the previous value
     * @since 1.8
     */
    // @HotSpotIntrinsicCandidate
    fun getAndSetObject(o: Any?, offset: Long, newValue: Any?): Any? {
        val method = realClass.getDeclaredMethod("getAndSetLong", Object::class.java,Long::class.java,Object::class.java);
        return method.invoke(o, offset, newValue)
    }

    /**
     * Ensures that loads before the fence will not be reordered with loads and
     * stores after the fence; a "LoadLoad plus LoadStore barrier".
     *
     * Corresponds to C11 atomic_thread_fence(memory_order_acquire)
     * (an "acquire fence").
     *
     * A pure LoadLoad fence is not provided, since the addition of LoadStore
     * is almost always desired, and most current hardware instructions that
     * provide a LoadLoad barrier also provide a LoadStore barrier for free.
     * @since 1.8
     */
    // @HotSpotIntrinsicCandidate
    fun loadFence() {
        val method = realClass.getDeclaredMethod("loadFence");
        method.invoke(realObject)
    }

    /**
     * Ensures that loads and stores before the fence will not be reordered with
     * stores after the fence; a "StoreStore plus LoadStore barrier".
     *
     * Corresponds to C11 atomic_thread_fence(memory_order_release)
     * (a "release fence").
     *
     * A pure StoreStore fence is not provided, since the addition of LoadStore
     * is almost always desired, and most current hardware instructions that
     * provide a StoreStore barrier also provide a LoadStore barrier for free.
     * @since 1.8
     */
    // @HotSpotIntrinsicCandidate
    fun storeFence() {
        val method = realClass.getDeclaredMethod("storeFence");
        method.invoke(realObject)
    }

    /**
     * Ensures that loads and stores before the fence will not be reordered
     * with loads and stores after the fence.  Implies the effects of both
     * loadFence() and storeFence(), and in addition, the effect of a StoreLoad
     * barrier.
     *
     * Corresponds to C11 atomic_thread_fence(memory_order_seq_cst).
     * @since 1.8
     */
    // @HotSpotIntrinsicCandidate
    fun fullFence() {
        val method = realClass.getDeclaredMethod("fullFence");
        method.invoke(realObject)
    }

}