package com.yusufcansenturk.firebasewithmvvm.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.yusufcansenturk.firebasewithmvvm.data.model.Note
import com.yusufcansenturk.firebasewithmvvm.databinding.FragmentNoteDetailBinding
import com.yusufcansenturk.firebasewithmvvm.util.UiState
import com.yusufcansenturk.firebasewithmvvm.util.hide
import com.yusufcansenturk.firebasewithmvvm.util.show
import com.yusufcansenturk.firebasewithmvvm.util.toast
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class NoteDetailFragment : Fragment() {

    val TAG:String = ""
    lateinit var binding :FragmentNoteDetailBinding
    val viewModel :NoteViewModel by viewModels()
    var isEdit  = false
    var objNote:Note? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNoteDetailBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()
        binding.button.setOnClickListener {
            if (isEdit) {
                updateNote()
            }else {
                createNote()
            }
        }
    }

    private fun updateUI() {
        val type = arguments?.getString("type", null)
        type?.let {
            when(it) {
                "view" -> {
                    isEdit = false
                    binding.noteMsg.isEnabled = false
                    objNote = arguments?.getParcelable("note")
                    binding.noteMsg.setText(objNote?.text)
                    binding.button.hide()
                }
                "create" -> {
                    isEdit = false
                    binding.button.setText("Create")
                }
                "edit" -> {
                    isEdit = true
                    objNote = arguments?.getParcelable("note")
                    binding.noteMsg.setText(objNote?.text)
                    binding.button.setText("Update")
                }
            }
        }
    }

    private fun createNote() {
        if (validation()) {
            viewModel.addNote(
                Note(
                    id = "",
                    text = binding.noteMsg.text.toString(),
                    date = Date()
                )
            )
        }
        viewModel.addNote.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UiState.Loading -> {
                    binding.btnProgressAr.show()
                    binding.button.text = ""
                }
                is UiState.Failure -> {
                    binding.btnProgressAr.hide()
                    binding.button.text = "Create"
                    state.error?.let { toast(it) }
                }
                is UiState.Success -> {
                    binding.btnProgressAr.hide()
                    binding.button.text = "Create"
                    toast(state.data)
                }
            }
        }
    }

    private fun updateNote() {
        if (validation()) {
            viewModel.updateNote(
                Note(
                    id = objNote?.id ?: "",
                    text = binding.noteMsg.text.toString(),
                    date = Date()
                )
            )
        }
        viewModel.updateNote.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UiState.Loading -> {
                    binding.btnProgressAr.show()
                    binding.button.text = ""
                }
                is UiState.Failure -> {
                    binding.btnProgressAr.hide()
                    binding.button.text = "Update"
                    state.error?.let { toast(it) }
                }
                is UiState.Success -> {
                    binding.btnProgressAr.hide()
                    binding.button.text = "Update"
                    toast(state.data)
                }
            }
        }
    }

    private fun validation(): Boolean {
        var isValid = true

        if (binding.noteMsg.text.toString().isNullOrEmpty()){
            isValid = false
            toast("Enter message")
        }
        return isValid
    }


}