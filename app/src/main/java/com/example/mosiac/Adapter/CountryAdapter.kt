package com.example.mosiac.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mosiac.R

class CountryAdapter(private val country: List<String>, private val onItemClick: (String) -> Unit) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val countryTextView: TextView = itemView.findViewById(R.id.countryTextView)

        fun bind(country: String) {
            countryTextView.text = country
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryAdapter.CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryAdapter.CountryViewHolder, position: Int) {
        val country = country[position]
        holder.bind(country)
        holder.itemView.setOnClickListener { onItemClick(country) }
    }

    override fun getItemCount(): Int {
        return country.size
    }

}