package com.example.serveyapp_rifatmahmud_v2tech.ui

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.serveyapp_rifatmahmud_v2tech.R
import com.example.serveyapp_rifatmahmud_v2tech.ViewModel.ServeyVM


class NewServeyActivity : AppCompatActivity() {

    lateinit var tv: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_servey)

        tv = findViewById(R.id.textView2);

        val viewModel: ServeyVM = ViewModelProvider(this).get(ServeyVM::class.java)


        viewModel.getmResponse().observe(this, Observer<String> {
            tv.setText(it)
        })

        /*viewModel.getmResponse().observe(this, object : Observer<String> {
            override fun onChanged(@Nullable data: String?) {
                // update ui.
                tv.setText(data)
            }
        })*/

    }
}