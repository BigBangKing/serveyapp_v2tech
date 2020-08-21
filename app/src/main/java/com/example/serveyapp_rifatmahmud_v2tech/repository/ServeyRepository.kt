package com.example.serveyapp_rifatmahmud_v2tech.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.serveyapp_rifatmahmud_v2tech.data.pojo.schema2x
import com.google.gson.Gson


class ServeyRepository {
    private var mResponse: MutableLiveData<String>? = null

     fun StartFetching(application: Application) {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(application.baseContext)
        val url = "https://example-response.herokuapp.com/getSurvey"
//        val url = "https://www.google.com/"

         Log.d("test", "langur execute hua")

        /* val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
             { response ->
//                 textView.text = "Response: %s".format(response.toString())
                 Log.v("test", response.substring(100))

             },
             { error ->
                 Log.v("test", "response is nothing :(")
             }
         )

        // Access the RequestQueue through your singleton class.
         queue.add(jsonObjectRequest)*/
        // Request a string response from the provided URL.
        /*val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                mResponse?.setValue(response.substring(100))
                Log.v("test", response)
            },
            {
                Toast.makeText(application, "Could not get data.", Toast.LENGTH_LONG).show()
                Log.v("test", "response is nothing")
            })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)*/

         val jsonObjectRequest =
             JsonObjectRequest(Request.Method.GET, url, null, {
                 val gson = Gson()

                 Log.d("test", it.toString())

                 val myResults = gson.fromJson(it.toString(), schema2x::class.java)

                Log.d("test", myResults.toString())

                 mResponse?.setValue(myResults.options)

             }, { })

         queue.add(jsonObjectRequest)

    }

    fun getmResponse(): MutableLiveData<String>? {
        if (mResponse == null) {
            mResponse = MutableLiveData<String>()
        }
        return mResponse
    }

}