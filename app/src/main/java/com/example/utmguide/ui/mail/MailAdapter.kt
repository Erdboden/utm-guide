package com.example.utmguide.ui.mail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.utmguide.R
import com.example.utmguide.model.Mail
import kotlinx.android.synthetic.main.item_mail.view.*

class MailAdapter(private val items: List<Mail>) : RecyclerView.Adapter<MailAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_mail,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(mail: Mail) {
            with(itemView){
                sender.text = mail.sender?.emailAddress?.name
                subject.text = mail.subject
                date.text = mail.receivedDateTime
                itemView.setOnClickListener{
                    findNavController().navigate(MailFragmentDirections.actionNavigationDashboardToNavigationSingleMail(mail))
                }
            }
        }
    }

}