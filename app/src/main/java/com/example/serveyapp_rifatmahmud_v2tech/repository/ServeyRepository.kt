package com.example.serveyapp_rifatmahmud_v2tech.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.serveyapp_rifatmahmud_v2tech.data.pojo.schema2x
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class ServeyRepository {
    private var mResponse: MutableLiveData<String>? = null

    fun StartFetching(application: Application) {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(application.baseContext)
        val url = "https://example-response.herokuapp.com/getSurvey"
//        val url = "https://www.google.com/"

        Log.d("test", "required method executed")


        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                mResponse?.setValue(response.toString())
                Log.v("test", response)
            },
            {
                it.printStackTrace()
                Toast.makeText(application, "Could not get data.", Toast.LENGTH_LONG).show()
                Log.v("test", "response is nothing")
            })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)


        /* val jsonObjectRequest =
             JsonObjectRequest(Request.Method.GET, url, null, {
                 val gson = Gson()

                 Log.d("test", it.toString())

                 val responseListType: Type? = object : TypeToken<ArrayList<schema2x?>?>() {}.type

                 val userArray: schema2x? = gson.fromJson(it.toString(), schema2x::class.java)

 //                val jsonarray = JSONArray(strResponse)
 //                val myResults = gson.fromJson(it.toString(), schema2x::class.java)
 //                Log.d("test", myResults.toString())
 //                mResponse?.setValue(myResults.options)

             }, {
                 it.printStackTrace()
                 Log.d("test", "error happened.")
             })

         queue.add(jsonObjectRequest)
 */
    }

    fun getmResponse(): MutableLiveData<String>? {
        if (mResponse == null) {
            mResponse = MutableLiveData<String>()
        }
        return mResponse
    }

}