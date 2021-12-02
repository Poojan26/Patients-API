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
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import org.w3c.dom.Text

class TestActivity : AppCompatActivity() {
    lateinit var patient:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        var id = findViewById<TextView>(R.id.patient_id)
        var patient_name = findViewById<TextView>(R.id.ETname)
        var pat = intent.getStringExtra("patient_id")
        var name = intent.getStringExtra("patient")
        id.text = pat.toString()
        patient_name.text = name.toString()
        patient = intent.getStringExtra("patient_id").toString()

    }

    // Function for button send
    fun clickTest(view: android.view.View) {

        if (view.id == R.id.buttontests) {

            // Collect data from user
            val patient_id = findViewById<TextView>(R.id.patient_id).text.toString()
            val tdate = findViewById<EditText>(R.id.ETlname).text.toString()
            val nurse = findViewById<EditText>(R.id.ETaddress).text.toString()
            val type = findViewById<EditText>(R.id.ETdob).text.toString()
            val category = findViewById<EditText>(R.id.ETdep).text.toString()
            val readings = findViewById<EditText>(R.id.ETdoc).text.toString()

            // Instantiate the RequestQueue.
            val queue = Volley.newRequestQueue(this)
            val url = "https://patient-rest-api.herokuapp.com/patients/$patient/tests"
            val textView = findViewById<TextView>(R.id.det)

            // Post parameters
            val params = HashMap<String, String>()
            params["patient_id"] = patient
            //params["first_name"] = name
            params["date"] = tdate
            params["nurse_name"] = nurse
            params["type"] = type
            params["category"] = category
            params["readings"] = readings

            val jsonObject = JSONObject(params as Map<*, *>)

            // Request a string response from the provided URL.
            val request = JsonObjectRequest(
                Request.Method.POST, url, jsonObject,
                Response.Listener { response ->
                    // Display the first 500 characters of the response string.
                    textView.text = "Response: %s".format(response.toString())
                },
                Response.ErrorListener { textView.text = "That didn't work!" })

            // Add the request to the RequestQueue.
            queue.add(request)

            // Create an intent object
            val intent = Intent(this@TestActivity, MainActivity::class.java)


            // Start Subactivity
            startActivity(intent)

        }
    }
}
