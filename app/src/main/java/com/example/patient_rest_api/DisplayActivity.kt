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
import org.w3c.dom.Text

class DisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        var fname = intent.getStringExtra("fname")
        var lname = intent.getStringExtra("lname")
        var address = intent.getStringExtra("address")
        var dob = intent.getStringExtra("dob")
        var department = intent.getStringExtra("department")
        var doctor = intent.getStringExtra("doctor")
        var pat_id = intent.getStringExtra("id")

        // getting textviews
        var f_name = findViewById<TextView>(R.id.ETname)
        var l_name = findViewById<TextView>(R.id.ETlname)
        var add = findViewById<TextView>(R.id.ETaddress)
        var date = findViewById<TextView>(R.id.ETdob)
        var dep = findViewById<TextView>(R.id.ETdep)
        var doc = findViewById<TextView>(R.id.ETdoc)
        var id = findViewById<TextView>(R.id.ids)

        f_name.text = fname.toString()
        l_name.text = lname.toString()
        add.text = address.toString()
        date.text = dob.toString()
        dep.text = department.toString()
        doc.text = doctor.toString()
        id.text = pat_id.toString()

    }
    // Add new test
    fun createTests(view: android.view.View) {

        if (view.id == R.id.buttontest) {
            // Create an intent object
            val intent = Intent(this@DisplayActivity, TestActivity::class.java)
            var patient_id = findViewById<TextView>(R.id.ids).text.toString()
            var patient = findViewById<TextView>(R.id.ETname).text.toString()
            intent.putExtra("patient_id",patient_id)
            intent.putExtra("patient",patient)
            // Start Subactivity
            startActivity(intent)
        }

    }
}