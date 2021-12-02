package com.example.patient_rest_api
/*
Group-2
Milestone-4
    Poojan Jitendrakumar Patel [301228811]
    Matthew Maxwell [301200258]
    Sanjeevan Pushparaj [301213104]
*/
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // Get all patient data
    fun btnget(view: android.view.View) {

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://patient-rest-api.herokuapp.com/patients"
        val textView = findViewById<TextView>(R.id.textView2)

        // Request a string response from the provided URL.
        val request = JsonArrayRequest(Request.Method.GET,url,null,
            Response.Listener { response ->
                // Display the first 500 characters of the response string.
                //textView.text = "Response is: ${response.substring(0, 500)}"
                textView.text = "Response: %s".format(response.toString())

            },
            Response.ErrorListener { textView.text = "That didn't work!" })

        // Add the request to the RequestQueue.
        queue.add(request)

    }

    // Add new patient
    fun btnpost(view: android.view.View) {

        if (view.id == R.id.btnpost) {
            // Create an intent object
            val intent = Intent(this@MainActivity, CreateActivity::class.java)

            // Start Subactivity
            startActivity(intent)
        }

    }

    // Get patient by id
    fun btngetid(view: android.view.View) {

        if (view.id == R.id.btngetid) {
            // Create an intent object
            val intent = Intent(this@MainActivity, SearchActivity::class.java)

            // Start Subactivity
            startActivity(intent)
        }

    }

}