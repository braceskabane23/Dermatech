package com.laurens.myapplication.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.laurens.myapplication.R

class TipsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tips, container, false)

        // Tambahkan logika untuk tombol back_button
        val backButton: View = view.findViewById(R.id.tombol_kembali)
        backButton.setOnClickListener {
            // Kembali ke halaman sebelumnya
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TipsFragment().apply {
                arguments = Bundle().apply {
                    // Tambahkan parameter jika diperlukan
                }
            }
    }
}
