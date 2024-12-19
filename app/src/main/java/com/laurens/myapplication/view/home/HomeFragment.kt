package com.laurens.myapplication.view.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.laurens.myapplication.R
import com.laurens.myapplication.adapter.InformationAdapter
import com.laurens.myapplication.data.varClass
import com.laurens.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val list = ArrayList<varClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list.addAll(getListBanana())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showRecyclerGrid()
        ClickListener()
    }

    private fun ClickListener() {
        binding.buttonScan.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_scanFragment)
        }
        binding.tips.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_home_to_tipsFragment)
        }
        binding.Attracting.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_home_to_trickFragment)
        }
    }

    private fun getListBanana(): ArrayList<varClass> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)

        val listBird = ArrayList<varClass>()

        // Pastikan panjang array sesuai dengan ekspektasi sebelum menggunakan indeksnya
        val minSize = minOf(dataName.size, dataDescription.size, dataPhoto.length())

        for (i in 0 until minSize) {
            val bird = varClass(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listBird.add(bird)
        }
        dataPhoto.recycle()
        return listBird
    }


    private fun showRecyclerGrid() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        val birdAdapter = InformationAdapter(list)
        binding.recyclerView.adapter = birdAdapter
        birdAdapter.setOnItemClickCallback(object : InformationAdapter.OnItemClickCallback() {
            override fun onItemClicked(data: varClass) {
                val intent = Intent(requireActivity(), DetailHomeActivity::class.java)
                intent.putExtra("EXTRA_NAME", data.name)
                intent.putExtra("EXTRA_DESCRIPTION", data.description)
                intent.putExtra("EXTRA_PHOTO", data.photo)
                startActivity(intent)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}