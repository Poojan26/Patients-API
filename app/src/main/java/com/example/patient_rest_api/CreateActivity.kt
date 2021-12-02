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

class CreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
    }

    // Function for button send
    fun clickCreate(view: android.view.View) {

        if (view.id == R.id.buttoncreate) {

            // Collect data from user
            val name = findViewById<EditText>(R.id.ETname).text.toString()
            val lastname = findViewById<EditText>(R.id.ETlname).text.toString()
            val address = findViewById<EditText>(R.id.ETaddress).text.toString()
            val dob = findViewById<EditText>(R.id.ETdob).text.toString()
            val department = findViewById<EditText>(R.id.ETdep).text.toString()
            val doctor = findViewById<EditText>(R.id.ETdoc).text.toString()

            // Instantiate the RequestQueue.
            val queue = Volley.newRequestQueue(this)
            val url = "https://patient-rest-api.herokuapp.com/patients"
            val textView = findViewById<TextView>(R.id.details)
            val ide = findViewById<TextView>(R.id.ide)

            // Post parameters
            val params = HashMap<String, String>()
            params["first_name"] = name
            params["last_name"] = lastname
            params["address"] = address
            params["date_of_birth"] = dob
            params["department"] = department
            params["doctor"] = doctor

            val jsonObject = JSONObject(params as Map<*, *>)

            // Request a string response from the provided URL.
            val request = JsonObjectRequest(
                Request.Method.POST, url, jsonObject,
                Response.Listener { response ->
                    // Display the first 500 characters of the response string.
                    textView.text = "Response: %s".format(response.toString())
                    ide.text = response.get("_id").toString()


                },
                Response.ErrorListener { textView.text = "That didn't work!" })

            // Add the request to the RequestQueue.
            queue.add(request)

            var patient_id = findViewById<TextView>(R.id.ide).text.toString()


            // Create an intent object
            val intent = Intent(this@CreateActivity, DisplayActivity::class.java)
            intent.putExtra("fname",name)
            intent.putExtra("lname",lastname)
            intent.putExtra("address",address)
            intent.putExtra("dob",dob)
            intent.putExtra("deoartment",department)
            intent.putExtra("doctor",doctor)
            intent.putExtra("id",patient_id)


            // Start Subactivity
            startActivity(intent)

        }
    }
}