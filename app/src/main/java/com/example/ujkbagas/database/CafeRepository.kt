package com.example.ujkbagas.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ujkbagas.model.Menu
import com.example.ujkbagas.model.Pesanan

class CafeRepository(private val cafeDao: CafeDao) {
    val readAllMenuData : LiveData<List<Menu>> = cafeDao.readAllMenu()
    val readMenuMakananData : LiveData<List<Menu>> = cafeDao.getMakananMenu()
    val readMenuMinumanData : LiveData<List<Menu>> = cafeDao.getMinumanMenu()
    val readMenuDessertData : LiveData<List<Menu>> = cafeDao.getDessertMenu()
    val readAllPesanan : LiveData<List<Pesanan>> = cafeDao.readAllPesanan()
    val readPesananDapur : LiveData<List<Pesanan>> = cafeDao.readPesananDapur()


//    suspend fun getMenuData(string: String){
//        //Drop
//        val data = when(string) {
//            "Makanan" -> cafeDao.getMakananMenu()
//            "Minuman" -> cafeDao.getMinumanMenu()
//            "Desert" -> cafeDao.getDessertMenu()
//            else -> cafeDao.readAllMenu()
//        }
//    }

    suspend fun insertOneMenu(menu: Menu){
        cafeDao.insertOneMenu(menu)
    }
    suspend fun insertMenus(menus: List<Menu>){
        cafeDao.insertMenus(menus)
    }
    suspend fun insertOnePesanan(pesanan: Pesanan){
        cafeDao.insertOnePesanan(pesanan)
    }
    suspend fun insertListPesanan(listPesanan: List<Pesanan>){
        cafeDao.insertListPesanan(listPesanan)
    }
    suspend fun deletePemesanan(pesanan: Pesanan){
        cafeDao.deletePesanan(pesanan)
    }

    suspend fun updatePemesananTemp(nomorMeja :String){
        cafeDao.updatePesananTemp(nomorMeja)
    }

    fun readPemesananTemp(nomorMeja: String): LiveData<List<Pesanan>> {
        return cafeDao.readPesananTemp(nomorMeja)
    }






}