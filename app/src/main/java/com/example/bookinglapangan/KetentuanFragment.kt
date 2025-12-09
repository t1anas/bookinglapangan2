package com.example.bookinglapangan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bookinglapangan.databinding.FragmentKetentuanBinding

class KetentuanFragment : Fragment() {

    private var _binding: FragmentKetentuanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKetentuanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupContent()
    }

    private fun setupToolbar() {
        binding.btnBack.setOnClickListener {
            // Pop fragment dari back stack
            parentFragmentManager.popBackStack()
        }
    }

    private fun setupContent() {
        // Konten sudah di-set di XML, tapi bisa di-update di sini jika perlu
        // binding.txtKetentuanContent.text = "Konten custom jika diperlukan"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = KetentuanFragment()
    }
}