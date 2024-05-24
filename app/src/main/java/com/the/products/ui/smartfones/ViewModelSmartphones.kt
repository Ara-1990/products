package com.the.products.ui.smartfones

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.the.products.remote.ProductsModel
import com.the.products.remote.RetroInctance
import com.the.products.remote.RetroService
import kotlinx.coroutines.launch

class ViewModelSmartphones:ViewModel() {

    private val _text = MutableLiveData<ProductsModel>().apply {
    }
    val text: LiveData<ProductsModel> = _text


    private  val recyclersearch = MutableLiveData<ProductsModel>()
    val rsearch: LiveData<ProductsModel> = recyclersearch



    fun getSmartPhonesList() {

        viewModelScope.launch {
            val retroInctance = RetroInctance.getRetroInctance().create(RetroService::class.java)
            val cal = retroInctance.getSmartphonesList()
            _text.value = cal

        }

    }


    fun searchSmartPhones(searchText: String) {

        viewModelScope.launch {
            val retroInctance = RetroInctance.getRetroInctance().create(RetroService::class.java)
            val call = retroInctance.serchProducts(searchText)
            recyclersearch.value = call

        }

    }


}