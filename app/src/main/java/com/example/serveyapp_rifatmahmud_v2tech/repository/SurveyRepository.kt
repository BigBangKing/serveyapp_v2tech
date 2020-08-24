package com.example.serveyapp_rifatmahmud_v2tech.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.serveyapp_rifatmahmud_v2tech.data.pojo.schema2x
import com.google.gson.Gson

/*Copyright (c) 2020. Project modified/created by Rifat Mahmud. Any reuse of these projects should be credited to him.
Google code samples/related libraries are reused and allowed to reuse as mentioned in Google Codelab / respective public sources. Copyright information can be found on their respective repository/Sources. Use on your own responsibility.
*/

class SurveyRepository {
    private var mResponse: MutableLiveData<String>? = null
    private var serveyArray: MutableLiveData<Array<schema2x>>? = null


    fun StartFetching(application: Application) {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(application.baseContext)
        val url = "https://example-response.herokuapp.com/getSurvey?" + System.currentTimeMillis()

        Log.d("test", "required method executed")

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val gson = Gson()

                val serveys: Array<schema2x> = gson.fromJson(
                    response,
                    Array<schema2x>::class.java
                )

                Log.v("test", "serveys size "+serveys.size.toString())

                serveyArray?.value = serveys
                Log.v("test", response)
            },
            {
                it.printStackTrace()
                Toast.makeText(application, "Could not get data.", Toast.LENGTH_LONG).show()
                Log.v("test", "response is nothing")
            })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)

    }

    fun getmResponse(): MutableLiveData<String>? {
        if (mResponse == null) {
            mResponse = MutableLiveData()
        }
        return mResponse
    }

    fun getServeyArrays(): MutableLiveData<Array<schema2x>>? {
        if (serveyArray == null){
            serveyArray = MutableLiveData()
        }
        return serveyArray
    }

}