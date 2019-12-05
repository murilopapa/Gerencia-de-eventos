package com.example.treinokotlin.presentation.list.view.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.treinokotlin.R
import com.example.treinokotlin.model.Event
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
        holder.apply {
            name.text = event.name
            date.text = event.date
            local.text = event.local
            description.text = event.description

        }

    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int) = if (position == 0) 1 else 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        if (viewType == 1) {
            ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.event_item_bigger, parent, false)
            )
        } else {
            ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.event_item, parent, false)
            )
        }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.event_item_name
        val date = itemView.event_item_date
        val local = itemView.event_item_local
        val description = itemView.event_item_description
    }

}
