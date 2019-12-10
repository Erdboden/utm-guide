package com.example.utmguide.ui.mail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.example.utmguide.R
import kotlinx.android.synthetic.main.fragment_mail.*


class MailFragment : Fragment() {

    private lateinit var mailViewModel: MailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mailViewModel = ViewModelProviders
            .of(this, MailViewModel.Factory(requireContext()))
            .get(MailViewModel::class.java)
        return inflater.inflate(R.layout.fragment_mail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()

        mailViewModel.mail.observe(this, Observer {
            mail_list.adapter = MailAdapter(it) { mailId -> mailViewModel.isRead(mailId, true) }
        })

    }

    private fun setupRecycler() {
        mail_list.layoutManager = LinearLayoutManager(requireActivity())

        val dividerItemDecoration = DividerItemDecoration(
            requireContext(),
            HORIZONTAL
        )
        mail_list.addItemDecoration(dividerItemDecoration)
    }
}