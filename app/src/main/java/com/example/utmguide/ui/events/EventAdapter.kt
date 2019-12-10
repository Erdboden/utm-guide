package com.example.utmguide.ui.events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.utmguide.R
import com.example.utmguide.model.Event
import com.example.utmguide.model.EventStatus
import com.example.utmguide.model.Status
import kotlinx.android.synthetic.main.item_event.view.*
import kotlinx.android.synthetic.main.item_event.view.subject
import kotlinx.android.synthetic.main.item_mail.view.*
import java.text.SimpleDateFormat

class EventAdapter(val items: List<Event>, val myMail: String) :
    RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_event,
                parent,
                false
            ), myMail
        )

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(itemView: View, val myMail: String) : RecyclerView.ViewHolder(itemView) {
        fun bind(event: Event) {
            with(itemView) {
                organizer.text = event.organizer.emailAddress?.name
                subject.text = event.subject
                location.text = event.location.displayName

                val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.0000000")
                val output = SimpleDateFormat("HH:mm dd/MM/yyyy")


                start.text = output.format(input.parse(event.start.dateTime))
                end.text = output.format(input.parse(event.end.dateTime))


                val filteredList = event.attendees?.filter { it.emailAddress.address == myMail }
                if (filteredList != null && filteredList.isNotEmpty()) {
                    when (filteredList[0].status.response) {
                        EventStatus.ACCEPTED.status -> status.setImageDrawable(
                            AppCompatResources.getDrawable(
                                this.context,
                                R.drawable.ic_accepted
                            )
                        )
                        EventStatus.DECLINED.status -> status.setImageDrawable(
                            AppCompatResources.getDrawable(
                                this.context,
                                R.drawable.ic_declined
                            )
                        )
                        EventStatus.TENTATIVELY_ACCEPTED.status -> status.setImageDrawable(
                            AppCompatResources.getDrawable(this.context, R.drawable.ic_tentative)
                        )
                        EventStatus.NONE.status -> status.setImageDrawable(
                            AppCompatResources.getDrawable(
                                this.context,
                                R.drawable.ic_none
                            )
                        )
                    }
                } else {
                    status.setImageDrawable(
                        AppCompatResources.getDrawable(
                            this.context,
                            R.drawable.ic_organiser
                        )
                    )
                }

                itemView.setOnClickListener {
                    findNavController().navigate(
                        EventsFragmentDirections.actionNavigationDashboardToSingleEventFragment(
                            event
                        )
                    )
                }
            }
        }
    }

}