package com.example.tribalw.ui.home

import android.app.Dialog
import android.content.ContextWrapper
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentDialog
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.tribalw.R
import com.example.tribalw.data.provider.local.entity.NoteEntity
import com.example.tribalw.presentation.ViewModelNote
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


@AndroidEntryPoint
class CreateNoteFragment : DialogFragment(){

    private val noteViewModel by viewModels<ViewModelNote>()
    private lateinit var alert : AlertDialog.Builder
    private lateinit var alertLoader : AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.DialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )

        val buttonCreateNote: Button = view.findViewById(R.id.buttonCreateNote) as MaterialButton
        buttonCreateNote.setOnClickListener {
            createAlertDialog()
            observerState()
        }

        val toolbar: MaterialToolbar = view.findViewById(R.id.materialToolbar_createNote) as MaterialToolbar
        val context = (context as ContextWrapper).baseContext
        (context as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { dismiss() }
    }

    override fun onResume() {
        super.onResume()

        /*(dialog as ComponentDialog).onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            dismiss()
        }*/

        requireActivity().onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    dismiss()
                }
            })



    }


    private fun observerState() {
        val titleTextInputEditText: TextInputEditText = view?.findViewById(R.id.ed_title) as TextInputEditText
        val descriptionTextInputEditText: TextInputEditText = view?.findViewById(R.id.ed_description) as TextInputEditText

        var title = titleTextInputEditText.text.toString()
        var description = descriptionTextInputEditText.text.toString()
        var validate: Boolean = true

        if (title.isNullOrEmpty()) {
            titleTextInputEditText.error = "Ingresa el titulo de tú Nota"
            validate = false
        } else {
            validate = true
        }

        if (description.isNullOrEmpty()) {
            titleTextInputEditText.error = "Ingresa la descripción tú nota"
            validate = false
        } else {
            validate = true
        }



        if (validate) {
            Log.i("validate", "cargandoo")

            /*alert.show()
            alertLoader.dismiss()*/

            noteViewModel.saveNotesEntityRoomVM(
                NoteEntity(title = title, description = description)
            )

            Toast.makeText(
                requireContext(),
                "Nota guardada con Exito",
                Toast.LENGTH_LONG
            ).show()

            dismiss()

        }
    }

    private fun createAlertDialog() {
        alert = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater

        alert.setView(inflater.inflate(R.layout.dialog_create, null))
            .setCancelable(false)

        alertLoader = alert.create()
    }



}