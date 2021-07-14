package com.viel.proxysample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sdkInterface: SDKInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sdkInterface = SDKInterface.getInstance(this)

        findViewById<View>(R.id.btn).setOnClickListener {
            sdkInterface.sdkFunction()
        }
    }
}