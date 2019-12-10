package com.example.utmguide.ui.mail.single


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.utmguide.R
import com.example.utmguide.model.Mail
import com.example.utmguide.ui.mail.MailViewModel
import kotlinx.android.synthetic.main.fragment_single_mail.*


/**
 * A simple [Fragment] subclass.
 */
class SingleMailFragment : Fragment() {
    private lateinit var singleMailViewModel: SingleMailViewModel
    private lateinit var mail: Mail

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        singleMailViewModel = ViewModelProviders
            .of(this, SingleMailViewModel.Factory(requireContext()))
            .get(SingleMailViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_mail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mail = SingleMailFragmentArgs.fromBundle(arguments!!).mail
        body.loadData(
            mail.body?.content,
            "text/html; charset=utf-8",
            "UTF-8"
        )

        reply_button.setOnClickListener {
            showReplyDialog()
        }
    }

    private fun showReplyDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Title")

// Set up the input
        // Set up the input
        val input = EditText(requireActivity())
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.inputType = InputType.TYPE_TEXT_FLAG_MULTI_LINE
        builder.setView(input)

// Set up the buttons
        // Set up the buttons
        builder.setPositiveButton(
            "OK"
        ) { dialog, _ ->
            singleMailViewModel.reply(
                mail.id,
                mail.sender!!,
                input.text.toString()
            )
            singleMailViewModel.replySent.observe(this, Observer {
                if (it) {
                    dialog.dismiss()
                }
            })
        }
        builder.setNegativeButton(
            "Cancel"
        ) { dialog, _ -> dialog.cancel() }

        builder.show()
    }

}
