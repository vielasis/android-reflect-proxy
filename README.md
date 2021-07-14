# Android Reflection + Proxy Sample

### About
This shows how can you utilize Java reflection and proxies to avoid repeating code. The interface provided is a very trivial example but can definitely be expanded to more complex scenarios/use-cases.
This is especially useful if you are working with multiple variants since it allows you to implement interfaces only when necessary

You can try to run the app module to see the differences between the variants. Only the `paid` variant has the implementation, other variants are obtaining a proxy instance.

### Important classes

[SDKInterface.kt](https://github.com/vielasis/android-reflect-proxy/blob/master/app/src/main/java/com/viel/proxysample/SDKInterface.kt) - main source set

This essentially is the entrypoint for any feature that you want to "split" the implementation among the variants. The explanation is detailed in the source of this file.
The overall pattern is essentially:
```kotlin
interface InterfaceName {
    fun funcName(...)

    companion object {
        fun getInstance(...) {
            // try to find the implementation classes
            // if not found, return the default impl or a Proxy
        }
    }
}
```
[SDKInterfaceImpl.kt](https://github.com/vielasis/android-reflect-proxy/blob/master/app/src/paid/java/com/viel/proxysample/SDKInterfaceImpl.kt) - paid source set

The actual implementation. It is only implemented here and not anywhere else on other flavors

### Obfuscation
This will definitely work with Proguard and R8. It is not included here, I leave that as an assignment to the reader
Hint: Learn about `@Keep` and `-keepclassmembers`