package com.the.products.ui.mainfrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayoutMediator

import com.the.products.databinding.FragmentMainBinding
import com.the.products.ui.leptops.Leptops
import com.the.products.ui.smartfones.Smartfhones
import com.the.products.ui.VpAdapter
import com.the.products.ui.fragrances.Fragrances
import com.the.products.ui.groceries.Groceries
import com.the.products.ui.homedecoration.HomeDecoration
import com.the.products.ui.skincare.Skincare

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null


    private val binding get() = _binding!!


    private var tlist = listOf(
        "Smartphones",
        "Laptops",
        "Fragrances",
        "Skincare",
        "Groceries",
        "Home decoration"

    )


    private var flist = listOf(
        Smartfhones.newInstance(),
        Leptops.newInstance(),
        Fragrances.newInstance(),
        Skincare.newInstance(),
        Groceries.newInstance(),
        HomeDecoration.newInstance()

    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {


        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root: View = binding.root

        init()


        return root
    }


    private fun init() = with(binding) {

        val adapter = VpAdapter(activity as FragmentActivity, flist)

        vp.adapter = adapter


        TabLayoutMediator(tablayout, vp) {
                tab, pos ->
            tab.text = tlist[pos]
        }.attach()

    }
}