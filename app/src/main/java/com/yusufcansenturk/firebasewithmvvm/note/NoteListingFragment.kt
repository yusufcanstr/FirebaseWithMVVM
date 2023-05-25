package com.yusufcansenturk.firebasewithmvvm.note

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.yusufcansenturk.firebasewithmvvm.databinding.FragmentNoteListingBinding
import com.yusufcansenturk.firebasewithmvvm.util.UiState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NoteListingFragment : Fragment() {

    lateinit var binding: FragmentNoteListingBinding
    val TAG :String = "NoteListingFragment"
    val viewModel :NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentNoteListingBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getNotes()
        viewModel.note.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UiState.Loading -> {
                    Log.e(TAG, "Loading")
                }
                is UiState.Failure -> {
                    Log.e(TAG, state.error.toString())
                }
                is UiState.Success -> {
                    state.data.forEach {
                        Log.e(TAG, it.toString())
                    }
                }
            }
        }

    }


}