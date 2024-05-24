package com.the.products.ui.skincare

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
import com.the.products.databinding.FragmentSkincareBinding
import com.the.products.databinding.FragmentSmartfhonesBinding
import com.the.products.remote.ProductsModel
import com.the.products.ui.smartfones.SmartPhonesAdapter
import com.the.products.ui.smartfones.ViewModelSmartphones
import kotlinx.coroutines.launch
import java.util.ArrayList


class Skincare : Fragment() {

    private var _binding: FragmentSkincareBinding? = null

    lateinit var recylerviewAdapter: SkincareAdapter

    lateinit var viewModel: ViewModelSkincare


    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(this).get(ViewModelSkincare::class.java)

        _binding = FragmentSkincareBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.recyclerview.layoutManager = LinearLayoutManager(context)

        val decoration = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)


        binding.recyclerview.addItemDecoration(decoration)
        recylerviewAdapter = SkincareAdapter()


        binding.recyclerview.adapter = recylerviewAdapter

        viewModel.rsearch.observe(viewLifecycleOwner, Observer<ProductsModel?> {

            recylerviewAdapter.submitList(it.products)



        })

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override  fun onQueryTextSubmit(query: String?): Boolean {

                if (query != null){
                    lifecycleScope.launch {
                        viewModel.searchSkincare(query)

                    }

                }
                return true
            }

            override  fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null){
                    viewModel.searchSkincare(newText)


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

        viewModel.getSkincareList()

        return root


    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = Skincare()
    }
}