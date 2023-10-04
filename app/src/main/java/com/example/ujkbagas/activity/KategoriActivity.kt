package com.example.ujkbagas.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.ujkbagas.R
import com.example.ujkbagas.databinding.ActivityKategoriBinding
import com.example.ujkbagas.intentKey.Key

class KategoriActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityKategoriBinding
    private var getNoMejaPesanan: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKategoriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Menu"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getNoMejaPesanan = intent.getStringExtra(Key.KEY_NO_MEJA)
        getNoMejaPesanan?.let { Log.d(Key.KEY_NO_MEJA, it) }

        binding.idbtnMakanan.setOnClickListener(this)
        binding.idbtnMinuman.setOnClickListener(this)
        binding.idbtnDessert.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.idbtn_makanan -> {
                val intent = Intent(this@KategoriActivity, ListMenuActivity::class.java)
                intent.putExtra(Key.KEY_NO_MEJA, getNoMejaPesanan)
                getNoMejaPesanan?.let { Log.d(Key.KEY_NO_MEJA, it) }
                intent.putExtra(Key.KEY_KATEGORI, "Makanan")
                startActivity(intent)
            }
            R.id.idbtn_minuman -> {
                val intent = Intent(this@KategoriActivity, ListMenuActivity::class.java)
                intent.putExtra(Key.KEY_NO_MEJA, getNoMejaPesanan)
                getNoMejaPesanan?.let { Log.d(Key.KEY_NO_MEJA, it) }
                intent.putExtra(Key.KEY_KATEGORI, "Minuman")
                startActivity(intent)
            }
            R.id.idbtn_dessert -> {
                val intent = Intent(this@KategoriActivity, ListMenuActivity::class.java)
                intent.putExtra(Key.KEY_NO_MEJA, getNoMejaPesanan)
                getNoMejaPesanan?.let { Log.d(Key.KEY_NO_MEJA, it) }
                intent.putExtra(Key.KEY_KATEGORI, "Dessert")
                startActivity(intent)
            }
        }

    }
}