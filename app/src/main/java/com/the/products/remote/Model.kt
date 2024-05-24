package com.the.products.remote

import retrofit2.Response

data class ProductsModel (val products:List<ProductsItemModel>)

data class ProductsItemModel(
    val id: Int, val title:String, val description:String,
    val price:Float, val discountPercentage:Float, val rating:Float, val stock:Int, val brand:String, val category:String,
     val image:String
     )
