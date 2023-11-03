package com.example.tribalw.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tribalw.R
import com.example.tribalw.data.provider.local.entity.NoteEntity
import com.example.tribalw.databinding.FragmentHomeBinding
import com.example.tribalw.presentation.ViewModelNote
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val noteViewModel by viewModels<ViewModelNote>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        eventsClick()
    }

    override fun onResume() {
        super.onResume()

        requireActivity().onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (!findNavController().navigateUp()) {
                        if (isEnabled) {
                            Log.i("back", "00000  atras dialog")
                            isEnabled = false
                            requireActivity().onBackPressedDispatcher.onBackPressed()
                        }
                        Log.i("back", "111  atras dialog")
                    } else {
                        var prueba = "falso"
                        Log.i("back", "2222 atras dialog")
                    }
                    Log.i("back", "atras dialog")
                }
            }
        )

    }

    private fun setupObservers() {
        noteViewModel.saveNotesEntityRoomVM(
            NoteEntity(title = "Enviar link del Repo Github", description =  "Prueba de talento android, guardado de datos local proveedor ROOM")
        )
    }

    private fun eventsClick() {
        binding.mbCreateNoteFragment.setOnClickListener {

            findNavController().navigate(R.id.action_navigation_home_to_createNoteFragment)

        }
    }


}