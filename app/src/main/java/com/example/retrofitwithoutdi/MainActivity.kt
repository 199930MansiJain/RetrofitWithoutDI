package com.example.retrofitwithoutdi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider




class MainActivity : AppCompatActivity() {
    private var viewModel: MainActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        viewModel!!.makeApiCall()
        viewModel!!.getRecyclerListObserver().observe(this) { data ->
            Log.e("getData",data.items.toString())
        }

    }
}