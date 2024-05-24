package com.the.products.ui.leptops

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.the.products.R
import com.the.products.databinding.RequestListBinding

import com.the.products.remote.ProductsItemModel
import java.util.ArrayList

class LeptopsAdapter: RecyclerView.Adapter<LeptopsAdapter.Rholder>() {


    private var emptyList = listOf<ProductsItemModel>()






    class Rholder(view: View): RecyclerView.ViewHolder(view){
        private val binding = RequestListBinding.bind(view)


        fun bind( productsItemModel: ProductsItemModel) = with(binding){
            title.text = productsItemModel.title
            description.text = productsItemModel.description
            price.text = productsItemModel.price.toString()
            discountPercentage.text = productsItemModel.discountPercentage.toString()
            rating.text = productsItemModel.rating.toString()
            stock.text = productsItemModel.stock.toString()
            brand.text = productsItemModel.brand
            category.text = productsItemModel.category
            Picasso.get().load(productsItemModel.image).into(imageView)

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Rholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.request_list, parent, false)
        return Rholder(view)
    }

    override fun onBindViewHolder(holder: Rholder, position: Int) {
        holder.bind(emptyList[position])


    }

    override fun getItemCount(): Int {
        return emptyList.size
    }

    fun setData(model: List<ProductsItemModel>){

        emptyList = model
        notifyDataSetChanged()
    }



}