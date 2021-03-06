package com.example.utmguide.ui.mail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.utmguide.R
import com.example.utmguide.model.Mail
import kotlinx.android.synthetic.main.item_mail.view.*
import java.text.SimpleDateFormat


class MailAdapter(private val items: List<Mail>, val read: (String) -> Unit) :
    RecyclerView.Adapter<MailAdapter.ViewHolder>() {
    override fun getItemViewType(position: Int): Int = when (items[position].isRead) {
        true -> R.layout.item_mail
        false -> R.layout.item_mail_unread
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                viewType,
                parent,
                false
            )
            , read
        )

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(itemView: View, val read: (String) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SimpleDateFormat")
        fun bind(mail: Mail) {

            with(itemView) {
                sender.text = mail.sender?.emailAddress?.name
                subject.text = mail.subject

                val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                val output = SimpleDateFormat("HH:mm dd/MM/yyyy")

                date.text = output.format(input.parse(mail.receivedDateTime))
                itemView.setOnClickListener {
                    findNavController().navigate(
                        MailFragmentDirections.actionNavigationDashboardToNavigationSingleMail(
                            mail
                        )
                    )
                    read.invoke(mail.id)
                }
            }
        }
    }

}