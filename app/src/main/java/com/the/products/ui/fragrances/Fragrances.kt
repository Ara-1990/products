package com.the.products.ui.fragrances

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.the.products.R
import com.the.products.databinding.FragmentFragrancesBinding
import com.the.products.databinding.FragmentLeptopsBinding
import com.the.products.databinding.FragmentSmartfhonesBinding
import com.the.products.remote.ProductsModel
import com.the.products.ui.leptops.LeptopsAdapter
import com.the.products.ui.smartfones.SmartPhonesAdapter
import com.the.products.ui.smartfones.ViewModelSmartphones
import kotlinx.coroutines.launch
import java.util.ArrayList


class Fragrances : Fragment() {

    private var _binding: FragmentFragrancesBinding? = null

    lateinit var recylerviewAdapter: FragrancesAdapter

    lateinit var viewModel: ViewModelFragrances


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFragrancesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel = ViewModelProvider(this).get(ViewModelFragrances::class.java)
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        val decoration = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)


        binding.recyclerview.addItemDecoration(decoration)
        recylerviewAdapter = FragrancesAdapter()


        binding.recyclerview.adapter = recylerviewAdapter

        viewModel.rsearch.observe(viewLifecycleOwner, Observer<ProductsModel?> {

            recylerviewAdapter.submitList(it.products)



        })

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override  fun onQueryTextSubmit(query: String?): Boolean {

                if (query != null){
                    lifecycleScope.launch {
                        viewModel.searchFragrances(query)

                    }

                }
                return true
            }

            override  fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null){
                    viewModel.searchFragrances(newText)


                }

                return true

            }

        })
        viewModel.text.observe(viewLifecycleOwner) {
            if (it == null) {
                Toast.makeText(context, "no result found", Toast.LENGTH_LONG).show()

            } else {

                recylerviewAdapter.submitList(it.products)
                recylerviewAdapter.notifyDataSetChanged()

            }

        }
        viewModel.getFragrancesList()

        return root


    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = Fragrances()
    }
}