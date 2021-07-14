package com.viel.proxysample

import android.content.Context
import android.widget.Toast

/**
 * Only implement where it is needed, in this case paid.
 *
 * free and premium flavor don't need to implement
 */
class SDKInterfaceImpl(private val context: Context) : SDKInterface {

    override fun sdkFunction() {
        Toast.makeText(context, "sdkFunction()", Toast.LENGTH_SHORT).show()
    }

    override fun sdkFunctionWithArg(arg: String) {
        Toast.makeText(context, "sdkFunction()", Toast.LENGTH_SHORT).show()
    }
}