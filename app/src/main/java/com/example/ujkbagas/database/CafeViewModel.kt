package com.example.ujkbagas.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import androidx.lifecycle.viewModelScope
import com.example.ujkbagas.model.Menu
import com.example.ujkbagas.model.Pesanan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CafeViewModel(application: Application):AndroidViewModel(application) {
    val readAllMenuData : LiveData<List<Menu>>
    val readMenuData : LiveData<List<Menu>>
    val readMenuMakananData : LiveData<List<Menu>>
    val readMenuMinumanData : LiveData<List<Menu>>
    val readMenuDessertData : LiveData<List<Menu>>
    val readAllPesanan : LiveData<List<Pesanan>>

    private val repository : CafeRepository

    init {
        val cafeDao = CafeDatabase.getDatabase(application).cafeDao()
        repository = CafeRepository(cafeDao)
        readAllMenuData = repository.readAllMenuData
        readMenuData = repository.readMenuData
        readMenuMakananData = repository.readMenuMakananData
        readMenuMinumanData = repository.readMenuMinumanData
        readMenuDessertData = repository.readMenuDessertData
        readAllPesanan = repository.readAllPesanan
    }

    fun getMenuData(string: String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMenuData(string)
        }
    }
    fun insertMenu(menu: Menu){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertOneMenu(menu)
        }
    }

    fun insertListMenu (listMenu : List<Menu>){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMenus(listMenu)
        }
    }

    fun insertPesanan(pesanan: Pesanan){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertOnePesanan(pesanan)
        }
    }

    fun insertListPesanan(listPesanan: List<Pesanan>){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertListPesanan(listPesanan)
        }
    }






}