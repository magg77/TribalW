package com.example.tribalw.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tribalw.R
import com.example.tribalw.core.valueObject.Resource
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

        setupLayout()
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

    private fun setupLayout() {
        binding.rvHomeFragment.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL, false
        )
    }

    private fun setupObservers() {
        noteViewModel.getAllNotesRoomVM().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    binding.progressbarHome.visibility = View.VISIBLE
                    /*binding.swipeHome.isRefreshing = true*/
                }

                is Resource.Success -> {
                    binding.progressbarHome.visibility = View.GONE
                    Log.i("lista", "${it.data}")

                    binding.rvHomeFragment.adapter = HomeAdapter(
                        requireContext(),
                        it.data,
                        onItemClickListener = {
                            var itemSelected = it
                        }
                    )

                }

                is Resource.Failure -> {
                    binding.progressbarHome.visibility = View.GONE

                    Log.e("homne", "${it.exception}")
                    Toast.makeText(
                        requireContext(),
                        "Ocurrio un error al traer los datos: ${it.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        })
    }

    private fun eventsClick() {
        binding.mbCreateNoteFragment.setOnClickListener {

            findNavController().navigate(R.id.action_navigation_home_to_createNoteFragment)

        }
    }


}