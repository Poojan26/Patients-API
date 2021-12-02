package com.example.patient_rest_api
/*
Group-2
Milestone-4
    Poojan Jitendrakumar Patel [301228811]
    Matthew Maxwell [301200258]
    Sanjeevan Pushparaj [301213104]
*/

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
    }

    // Get specific patient data
    fun btnSearch(view: android.view.View) {

        val editText = findViewById<EditText>(R.id.ETid).text.toString()

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://patient-rest-api.herokuapp.com/patients/$editText"
        val textView = findViewById<TextView>(R.id.textView4)

        // Request a string response from the provided URL.
        val request = JsonArrayRequest(
            Request.Method.GET,url,null,
            Response.Listener { response ->
                // Display the first 500 characters of the response string.
                //textView.text = "Response is: ${response.substring(0, 500)}"
                textView.text = "Response: %s".format(response.toString())

            },
            Response.ErrorListener { textView.text = "That didn't work!" })

        // Add the request to the RequestQueue.
        queue.add(request)


        // Display tests
        // Instantiate the RequestQueue.
        val queue1 = Volley.newRequestQueue(this)
        val url1 = "https://patient-rest-api.herokuapp.com/patients/$editText/tests"
        val textView2 = findViewById<TextView>(R.id.textView5)

        // Request a string response from the provided URL.
        val request1 = JsonArrayRequest(
            Request.Method.GET,url1,null,
            Response.Listener { response ->
                // Display the first 500 characters of the response string.
                //textView.text = "Response is: ${response.substring(0, 500)}"
                textView2.text = "Response: %s".format(response.toString())

            },
            Response.ErrorListener { textView2.text = "That didn't work!" })

        // Add the request to the RequestQueue.
        queue1.add(request1)

    }
}