package com.viel.proxysample

import android.content.Context
import android.util.Log
import java.lang.reflect.Proxy

interface SDKInterface {

    fun sdkFunction()

    fun sdkFunctionWithArg(arg: String)

    companion object {

        // 1. just save the package name
        private const val PKG = "com.viel.proxysample"

        // 2. create static factory method
        fun getInstance(context: Context): SDKInterface {

            // 3. Obtain the implementation class via reflection if it exists
            try {
                val cls = Class.forName(
                    "${PKG}.SDKInterfaceImpl", // [1]
                    false,
                    context.classLoader
                )
                // [1] - it should be the fully qualified class name (pkg + class name)
                // Enforce a coding standard - here all implementations should have "Impl" Suffix

                // 4. Check if it implements the interface
                if (!SDKInterface::class.java.isAssignableFrom(cls)) {
                    // isAssignableFrom equivalent to "instanceof" / "is"
                    throw Error("${cls.name} does not implement SDKInterface")
                }

                // 5. For now, lets assume all implementation take context as an arg
                val clsConstructor = cls.getConstructor(Context::class.java)
                return clsConstructor.newInstance(context) as SDKInterface
            } catch (e: Exception) {
                // 6. The impl doesn't exist. Return a stub
                // Of course you can provide a default implementation of your class instead like
                // return object : SDKInterface {
                //     override fun sdkFunction() {
                //         // no-op
                //     }
                //
                //     override fun sdkFunctionWithArg(arg: String) {
                //         // no-op
                //     }
                // }
                // 7. Or *Proxy* it:
                // https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Proxy.html
                val proxy = Proxy.newProxyInstance(
                    context.classLoader,
                    arrayOf(SDKInterface::class.java)
                ) { theProxy, method, methodArgs ->
                    // This is the InvocationHandler:
                    // https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/InvocationHandler.html

                    // theProxy -> the "self" reference so you don't capture references
                    // method -> the actual method reference
                    // methodArgs -> the args passed to the method, if any
                    // You can do anything you want here! But for now we just:

                    Log.d("Nothing", ":D")
                }
                return proxy as SDKInterface
            }
        }
    }
}