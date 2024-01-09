package com.madhavsolanki.apiintegration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import com.madhavsolanki.apiintegration.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val url = "https://api.github.com/users"
    private var userInfoItemList = mutableListOf<userInfoItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        getDataFromVolley()


    }

    private fun getDataFromVolley(){

        var recyclerView = binding.rvView
        val adaptor = Adoptor(this, userInfoItemList)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adaptor
        val stringRequest = StringRequest(url, Response.Listener {

            // We cannot show JSON Data into the application so we use GSON for converting
            // it to JAVA Object than the user can easily see that
            val gsonBuilder = GsonBuilder()
            val gson = gsonBuilder.create()

            // Instead of using ArrayList, use an array of userInfoItem
            val userInfoItemsArray = gson.fromJson(it, Array<userInfoItem>::class.java)

            // Convert the array to a mutable list
            userInfoItemList.addAll(userInfoItemsArray)

            // Notify the adapter that the data has changed
            adaptor.notifyDataSetChanged()
        }, Response.ErrorListener {
            Toast.makeText(this, "Something Went Wrong " + it.toString(), Toast.LENGTH_LONG).show()
        })

        val volleyQueue = Volley.newRequestQueue(this)
        volleyQueue.add(stringRequest)
    }

}