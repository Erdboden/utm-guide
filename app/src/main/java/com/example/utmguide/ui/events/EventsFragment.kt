package com.example.utmguide.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.utmguide.R
import kotlinx.android.synthetic.main.fragment_events.*

class EventsFragment : Fragment() {

    private lateinit var eventsViewModel: EventsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        eventsViewModel =
            ViewModelProviders.of(this, EventsViewModel.Factory(requireContext()))
                .get(EventsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_events, container, false)
        eventsViewModel.text.observe(this, Observer {

        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        event_list.layoutManager = LinearLayoutManager(requireContext())

        eventsViewModel.events.observe(this, Observer {
            event_list.adapter = EventAdapter(it.value)
        })

    }
}