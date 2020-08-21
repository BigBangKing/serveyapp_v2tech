package com.example.serveyapp_rifatmahmud_v2tech.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.serveyapp_rifatmahmud_v2tech.R
import com.example.serveyapp_rifatmahmud_v2tech.ViewModel.ServeyVM
import com.example.serveyapp_rifatmahmud_v2tech.data.pojo.schema2x
import com.example.serveyapp_rifatmahmud_v2tech.ui.fragments.ServeyFragment


class NewServeyActivity : AppCompatActivity() {

    lateinit var tv: TextView
    lateinit var progressBar: ProgressBar
    lateinit var button: Button
    lateinit var fragmentView: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_servey)

        tv = findViewById(R.id.textView2)
        progressBar = findViewById(R.id.progressBar)
        button = findViewById(R.id.button)
        button.visibility = View.INVISIBLE
//        fragmentView = findViewById(R.id.ServeyFragmentView)

        val viewModel: ServeyVM = ViewModelProvider(this).get(ServeyVM::class.java)


        viewModel.serveys.observe(this, Observer<Array<schema2x>> {
            if (progressBar.visibility == View.VISIBLE)
                progressBar.visibility = View.INVISIBLE
            tv.text = "Start Servey"
            button.visibility = View.VISIBLE

            for (i in it) {
                Log.d("test", "From activity: "+i.question)
            }
        })

        viewModel.shouldStartServey = MutableLiveData(false)

        val fragment = ServeyFragment()
        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.ServeyFragmentView, fragment)
        // Complete the changes added above
        ft.commit()

        button.setOnClickListener(View.OnClickListener {
            if (tv.visibility == View.VISIBLE)
                tv.visibility = View.INVISIBLE
            if (button.visibility == View.VISIBLE)
                button.visibility = View.INVISIBLE

            viewModel.shouldStartServey =  MutableLiveData(true)


        })
    }
}