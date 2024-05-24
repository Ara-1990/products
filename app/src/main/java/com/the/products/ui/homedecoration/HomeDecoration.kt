package com.the.products.ui.homedecoration

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
import com.the.products.databinding.FragmentHomeDecorationBinding
import com.the.products.remote.ProductsModel
import com.the.products.ui.fragrances.FragrancesAdapter
import com.the.products.ui.fragrances.ViewModelFragrances
import kotlinx.coroutines.launch
import java.util.ArrayList


class HomeDecoration : Fragment() {

    private var _binding: FragmentHomeDecorationBinding? = null

    lateinit var recylerviewAdapter: HomeDecorationAdapter

    lateinit var viewModel: ViewModelHomeDecoration


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeDecorationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel = ViewModelProvider(this).get(ViewModelHomeDecoration::class.java)
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        val decoration = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)

        binding.recyclerview.addItemDecoration(decoration)
        recylerviewAdapter = HomeDecorationAdapter()


        binding.recyclerview.adapter = recylerviewAdapter


        viewModel.rsearch.observe(viewLifecycleOwner, Observer<ProductsModel?> {

            recylerviewAdapter.submitList(it.products)



        })

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override  fun onQueryTextSubmit(query: String?): Boolean {

                if (query != null){
                    lifecycleScope.launch {
                        viewModel.searchHomeDecorationList(query)

                    }

                }
                return true
            }

            override  fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null){
                    viewModel.searchHomeDecorationList(newText)


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
        viewModel.getHomeDecorationList()

        return root



    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            HomeDecoration()
    }
}