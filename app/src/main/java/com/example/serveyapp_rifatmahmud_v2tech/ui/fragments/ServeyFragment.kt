package com.example.serveyapp_rifatmahmud_v2tech.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.serveyapp_rifatmahmud_v2tech.R
import com.example.serveyapp_rifatmahmud_v2tech.ViewModel.ServeyVM
import com.example.serveyapp_rifatmahmud_v2tech.data.pojo.schema2x

class ServeyFragment : Fragment() {

    private val model: ServeyVM by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.servey_fragment, container, false)

        val tv:TextView = view.findViewById(R.id.textView2)

        var x: Array<schema2x>
        model.serveys.observe(viewLifecycleOwner, Observer {
            x = it
            tv.setText(x[0].question)
        })

        val hider: View = view.findViewById(R.id.HideBackgrounds)

        model.shouldStartServey.observe(viewLifecycleOwner, Observer {
            Log.d("test", "hider data changes")

            if (model.shouldStartServey == MutableLiveData(true)) {
                Log.d("test", "hider should remove")
                hider.visibility = View.GONE
            }
        })

        return view
    }
}