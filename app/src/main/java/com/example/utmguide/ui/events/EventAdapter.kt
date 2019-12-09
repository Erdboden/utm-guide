package com.example.utmguide.ui.events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.utmguide.R
import com.example.utmguide.model.Event
import kotlinx.android.synthetic.main.item_event.view.*

class EventAdapter(val items: List<Event>) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_event,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(event: Event) {
            with(itemView) {
                organizer.text = event.organizer.name
                subject.text = event.subject
                location.text = event.location.displayName
                start.text = event.start.dateTime
                end.text = event.end.dateTime
            }
        }
    }

}