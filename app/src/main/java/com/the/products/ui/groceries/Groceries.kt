package com.the.products.ui.groceries

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
import com.the.products.databinding.FragmentGroceriesBinding
import com.the.products.remote.ProductsModel
import com.the.products.ui.fragrances.FragrancesAdapter
import com.the.products.ui.fragrances.ViewModelFragrances
import com.the.products.ui.smartfones.SmartPhonesAdapter
import kotlinx.coroutines.launch
import java.util.ArrayList


class Groceries : Fragment() {

    private var _binding: FragmentGroceriesBinding? = null

    lateinit var recylerviewAdapter: GroceriesAdapter

    lateinit var viewModel: ViewModelGroceries


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentGroceriesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel = ViewModelProvider(this).get(ViewModelGroceries::class.java)
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        val decoration = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)


        binding.recyclerview.addItemDecoration(decoration)
        recylerviewAdapter = GroceriesAdapter()


        binding.recyclerview.adapter = recylerviewAdapter

        viewModel.rsearch.observe(viewLifecycleOwner, Observer<ProductsModel?> {

            recylerviewAdapter.submitList(it.products)



        })

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override  fun onQueryTextSubmit(query: String?): Boolean {

                if (query != null){
                    lifecycleScope.launch {
                        viewModel.searchGroceries(query)

                    }

                }
                return true
            }

            override  fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null){
                    viewModel.searchGroceries(newText)


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

        viewModel.getGroceriesList()

        return root

    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = Groceries()

    }
}