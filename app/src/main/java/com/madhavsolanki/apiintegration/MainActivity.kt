package com.madhavsolanki.apiintegration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.madhavsolanki.apiintegration.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    val url = "https://api.github.com/users"
    val userInfoItem = arrayListOf<userInfoItem>()

    // Creating Object of userInfo Class
    val userInfo = userInfo()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


       val stringRequest = StringRequest(url, Response.Listener {

       }, Response.ErrorListener {

       })

    }
}