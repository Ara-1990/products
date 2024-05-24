package com.the.products.ui.leptops

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
import com.the.products.databinding.FragmentLeptopsBinding
import com.the.products.remote.ProductsModel
import kotlinx.coroutines.launch
import java.util.ArrayList


class Leptops : Fragment() {

    private var _binding: FragmentLeptopsBinding? = null

    private val binding get() = _binding!!

    lateinit var recylerviewAdapter: LeptopsAdapter
    lateinit var viewModel: ViewModelLeptops




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLeptopsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel = ViewModelProvider(this).get(ViewModelLeptops::class.java)

        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        val decoration = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)


        binding.recyclerview.addItemDecoration(decoration)
        recylerviewAdapter = LeptopsAdapter()


        binding.recyclerview.adapter = recylerviewAdapter

        viewModel.rsearch.observe(viewLifecycleOwner, Observer<ProductsModel?> {

            recylerviewAdapter.setData(it.products)



        })

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override  fun onQueryTextSubmit(query: String?): Boolean {

                if (query != null){
                    lifecycleScope.launch {
                        viewModel.searchLeptops(query)

                    }

                }
                return true
            }

            override  fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null){
                    viewModel.searchLeptops(newText)


                }

                return true

            }

        })

        viewModel.text.observe(viewLifecycleOwner) {
            if (it == null) {
                Toast.makeText(context, "no result found", Toast.LENGTH_LONG).show()

            } else {

                recylerviewAdapter.setData(it.products)
                recylerviewAdapter.notifyDataSetChanged()

            }

        }

        viewModel.getLeptopsList()

        return root

    }

    companion object {
        @JvmStatic
        fun newInstance() = Leptops()
    }


}