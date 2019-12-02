package com.example.treinokotlin.presentation.list.view.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.treinokotlin.Event
import com.example.treinokotlin.R
import kotlinx.android.synthetic.main.event_item.view.*

class EventListAdapter : RecyclerView.Adapter<EventListAdapter.ViewHolder>() {

    private var data = mutableListOf<Event>()

    fun setData(events: List<Event>) {
        data.clear()
        data.addAll(events)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = data[position]
        holder.name.text = event.name
        holder.date.text = event.date
        holder.local.text = event.local
        holder.description.text = event.description

    }

    override fun getItemCount(): Int = data.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_item, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.event_item_name
        val date = itemView.event_item_date
        val local = itemView.event_item_local
        val description = itemView.event_item_description
    }
}
