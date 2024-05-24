package com.the.products.remote

import androidx.lifecycle.LiveData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("products/category/smartphones")
    suspend fun getSmartphonesList(): ProductsModel




    @GET("products/category/laptops")
    suspend fun getLeptopsList(): ProductsModel

    @GET("products/category/fragrances")
    suspend fun getFragrancesList(): ProductsModel

    @GET("products/category/skincare")
    suspend fun getSkincareList(): ProductsModel

    @GET("products/category/groceries")
    suspend fun getGroceriesList(): ProductsModel

    @GET("products/category/home-decoration")
    suspend fun getHomedecorationList(): ProductsModel


    @GET("products/search")
    suspend fun serchProducts(@Query("q")searchText:String):ProductsModel

}