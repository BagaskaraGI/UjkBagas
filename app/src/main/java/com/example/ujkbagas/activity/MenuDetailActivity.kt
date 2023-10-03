package com.example.ujkbagas.activity

import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.ujkbagas.R
import com.example.ujkbagas.database.CafeViewModel
import com.example.ujkbagas.databinding.ActivityListMenuBinding
import com.example.ujkbagas.databinding.ActivityMenuDetailBinding
import com.example.ujkbagas.model.Menu
import java.util.Date
import java.util.Locale

class MenuDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMenuDetailBinding

    private lateinit var mCafeViewModel: CafeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val menuData = intent.getParcelableExtra<Menu>("Data Menu")

        if (menuData != null) {
            binding.idtvNama.text = menuData.namaMenu
            binding.idtvDeskripsi.text = menuData.deskripsiMenu
            binding.idtvHarga.text = "HARGA : ${menuData.hargaMenu}"
            Glide.with(this)
                .load(menuData.gambarMenu)
                .fitCenter()
                .into(binding.idimgKategori)



        }

    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        val date = Date()

        return dateFormat.format(date)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}