package com.example.mosiac

import android.content.Intent
import android.content.res.Resources
import android.content.res.XmlResourceParser
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mosiac.Adapter.CountryAdapter
import org.xmlpull.v1.XmlPullParser

class MainActivity : AppCompatActivity() {

    private fun parseCountry(resources: Resources): List<String> {
        val list = ArrayList<String>()

        val xml = resources.getIdentifier("countries","xml",packageName)

        val parse: XmlPullParser = resources.getXml(xml)

        while (parse.next() != XmlPullParser.END_DOCUMENT) {
            if (parse.eventType == XmlPullParser.START_TAG && parse.name == "item"){
                val country = parse.nextText()
                list.add(country)
            }
        }

        return list
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val country = parseCountry(resources)

        val recyclerView: RecyclerView = findViewById(R.id.country_view)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter = CountryAdapter(country) { country ->
            println("Selected country: $country")

            val i = Intent(this, CountryActivity::class.java)
            i.putExtra("countryName", country)
            startActivity(i)
        }
        recyclerView.adapter = adapter
    }
}