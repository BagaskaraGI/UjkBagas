package com.example.ujkbagas.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ujkbagas.model.Menu
import com.example.ujkbagas.model.Pesanan

@Dao
interface CafeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOneMenu(menu: Menu)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMenus(menus: List<Menu>)


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOnePesanan(pesanan: Pesanan)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertListPesanan(listPesanan: List<Pesanan>)


    @Query("SELECT * FROM menu_table ORDER BY id ASC")
    fun readAllMenu(): LiveData<List<Menu>>
    //Hanya Mengambil Data Makanan
    @Query("SELECT * FROM menu_table WHERE kategoriMenu = 'Makanan'")
    fun getMakananMenu(): LiveData<List<Menu>>
    //Hanya Mengambil Data Minuman
    @Query("SELECT * FROM menu_table WHERE kategoriMenu = 'Minuman'")
    fun getMinumanMenu(): LiveData<List<Menu>>

    @Query("SELECT * FROM menu_table WHERE kategoriMenu = 'Dessert'")
    fun getDessertMenu(): LiveData<List<Menu>>


    @Query("SELECT * FROM pesanan_table ORDER BY waktuPesan DESC")
    fun readAllPesanan(): LiveData<List<Pesanan>>


    @Query("SELECT * FROM pesanan_table WHERE nomerMeja = :nomorMeja AND statusPesan = 'Belum Dipesan' ORDER BY waktuPesan DESC")
    fun readPesananTemp(nomorMeja : String): LiveData<List<Pesanan>>

    @Query("UPDATE pesanan_table SET statusPesan = 'Sudah Dipesan' WHERE nomerMeja = :nomerMeja AND statusPesan = 'Belum Dipesan'")
    fun updatePesananTemp(nomerMeja : String)


    @Query("SELECT * FROM pesanan_table WHERE statusPesan = 'Sudah Dipesan' ORDER BY waktuPesan DESC")
    fun readPesananDapur(): LiveData<List<Pesanan>>


    @Delete
    fun deletePesanan(pesanan: Pesanan)




}
