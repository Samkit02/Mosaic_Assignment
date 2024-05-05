package com.example.mosiac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class CountryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val countryName = intent.getStringExtra("countryName")

        val api = "https://restcountries.com/v3.1/name/$countryName"
        val request = JsonArrayRequest(
            Request.Method.GET, api, null,
            { response ->
                try {
                    val country = response.getJSONObject(0)

                    val name = country.getJSONObject("name").getString("common")
                    val capital = country.getJSONArray("capital").getString(0)
                    val population = country.getInt("population").toString()
                    val area = country.getDouble("area").toString()
                    val region = country.getString("region")
                    val subregion = country.getString("subregion")

                    println("Selected country capital: $capital")

                    findViewById<TextView>(R.id.countryNameTextView).text = "Country: $name"
                    findViewById<TextView>(R.id.capitalTextView).text = "Capital: $capital"
                    findViewById<TextView>(R.id.populationTextView).text = "Population: $population"
                    findViewById<TextView>(R.id.areaTextView).text = "Area: $area sq km"
                    findViewById<TextView>(R.id.regionTextView).text = "Region: $region"
                    findViewById<TextView>(R.id.subregionTextView).text = "Sub-region: $subregion"
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(this, "Error parsing JSON", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                error.printStackTrace()
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            })

        Volley.newRequestQueue(this).add(request)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}