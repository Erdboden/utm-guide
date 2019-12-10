package com.example.utmguide.ui.events.single


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.utmguide.R
import com.example.utmguide.ui.mail.single.SingleMailFragmentArgs
import kotlinx.android.synthetic.main.fragment_single_mail.*

/**
 * A simple [Fragment] subclass.
 */
class SingleEventFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_event, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        body.loadData(SingleEventFragmentArgs.fromBundle(arguments!!).event.body?.content,"text/html; charset=utf-8", "UTF-8")
    }
}
