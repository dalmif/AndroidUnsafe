# AndroidUnsafe
access to internal sun.misc.Unsafe API using reflection in android
In Android, the `sun.misc.Unsafe.java` class is not available for direct use by developers. While the class exists in the Android runtime, it is not part of the public Android API and is not intended for general application development.

## What is Unsafe?

`Unsafe.java` is a class in the Java Development Kit (JDK) that provides low-level, unsafe operations for advanced programming scenarios. It is part of the sun.misc package and is intended for internal use within the JDK itself. It is not a public API and is not intended to be used directly by application developers.

The Unsafe class allows direct manipulation of memory, object fields, and synchronization. It provides methods that bypass standard Java language restrictions and can be used to perform operations that are normally not possible or recommended in regular Java code. Some of the operations that can be performed using Unsafe include:

* Direct memory access: The Unsafe class allows direct access to native memory through methods like allocateMemory(), put*() and get*() methods to read and write values at specific memory addresses. This can be useful in scenarios where fine-grained control over memory is required, such as in certain advanced data structures or when interfacing with native code.

* Object field manipulation: Unsafe provides methods to directly read, write, or modify the values of object fields without going through the regular Java language access controls. This includes operations like getObject(), putObject(), getInt(), putInt(), etc. These operations can be used to modify fields regardless of their access modifiers, which can lead to unexpected behavior and violate encapsulation principles if used improperly.

* Synchronization primitives: Unsafe provides low-level synchronization primitives, such as monitorEnter() and monitorExit(), which allow explicit locking and unlocking of monitors on objects. These methods can be used to implement custom synchronization mechanisms or work with lower-level locking constructs.

It's important to note that using the Unsafe class requires a deep understanding of the Java memory model, synchronization, and low-level programming concepts. It should be used with caution and only in specific cases where the benefits outweigh the risks. Direct use of Unsafe is generally discouraged for regular application development because it can easily lead to unsafe, unstable, and non-portable code.

Unsafe class is not accessible by default through the standard API. 

## Usage

to use this repository just copy Unsage.kt into your project and use it like Example.kt
