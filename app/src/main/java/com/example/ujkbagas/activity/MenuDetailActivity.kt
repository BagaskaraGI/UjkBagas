package com.example.ujkbagas.activity

import android.content.ContentValues
import android.content.Intent
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.ujkbagas.R
import com.example.ujkbagas.database.CafeViewModel
import com.example.ujkbagas.databinding.ActivityListMenuBinding
import com.example.ujkbagas.databinding.ActivityMenuDetailBinding
import com.example.ujkbagas.intentKey.Key
import com.example.ujkbagas.model.Menu
import com.example.ujkbagas.model.Pesanan
import java.util.Date
import java.util.Locale

class MenuDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuDetailBinding

    private lateinit var mCafeViewModel: CafeViewModel
    private var getNoMejaPesanan: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuDetailBinding.inflate(layoutInflater)

        mCafeViewModel = ViewModelProvider(this).get(CafeViewModel::class.java)

        setContentView(binding.root)

        val menuData = intent.getParcelableExtra<Menu>("Data Menu")
        getNoMejaPesanan = intent.getStringExtra(Key.KEY_NO_MEJA)

        if (menuData != null) {
            binding.idtvNama.text = menuData.namaMenu
            binding.idtvDeskripsi.text = menuData.deskripsiMenu
            binding.idtvHarga.text = "HARGA : ${menuData.hargaMenu}"
            Glide.with(this)
                .load(menuData.gambarMenu)
                .fitCenter()
                .into(binding.idimgKategori)

            binding.idbtnPesan.setOnClickListener {
                showDialog(menuData, getNoMejaPesanan)
            }
        }

    }


    fun showDialog(menuData: Menu, noMeja: String?) {
        val title =
            if (noMeja != null) "Yakin ingin memesan ini?" else "Anda belum memilih No Meja makanan, ingin memulai pesanan?"
        val messege =
            if (noMeja != null) "No meja : $noMeja \nMenu : ${menuData.namaMenu} \nHarga : ${menuData.hargaMenu}" else "Pilih No Meja dulu ?"

        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(title)
            .setMessage(messege)
            .setPositiveButton("Ya") { _, _ ->
                if (noMeja != null) {
//                    val values = ContentValues()
//                    values.put(DatabaseContract.NoteColumn.NOMER_MEJA, noMeja)
//                    values.put(DatabaseContract.NoteColumn.NAMA, data.nama)
//                    values.put(DatabaseContract.NoteColumn.HARGA, data.harga)
//                    values.put(DatabaseContract.NoteColumn.WAKTU, getCurrentDate())
//                    val result = pesananHelper.insert(values)

                    val pesanan =
                        Pesanan(0, noMeja, menuData.namaMenu, menuData.hargaMenu, getCurrentDate(), "Belum Dipesan")

                    mCafeViewModel.insertPesanan(pesanan = pesanan)
//                    if (result > 0) {
//                        val intent = Intent(this, ListPesanan::class.java)
//                        intent.putExtra(Key.KEY_NO_MEJA, noMeja)
//                        startActivity(intent)
//                    }

                    val intent = Intent(this, ListPesananActivity::class.java)
                    intent.putExtra(Key.KEY_NO_MEJA, noMeja)
                    startActivity(intent)


                } else {
                    val intent = Intent(this, PesananActivity::class.java)
                    intent.putExtra(Key.KEY_MENU_PESANAN_TEMP, menuData)
                    intent.putExtra(Key.KEY_DASHBOARD, "Pilih No Meja")
                    startActivity(intent)
                }
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.cancel()
            }
            .create()
            .show()
    }


    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        val date = Date()

        return dateFormat.format(date)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}