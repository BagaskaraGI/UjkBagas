package com.example.ujkbagas.activity

import android.content.Intent
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.ujkbagas.R
import com.example.ujkbagas.database.CafeViewModel
import com.example.ujkbagas.databinding.ActivityPesananBinding
import com.example.ujkbagas.intentKey.Key
import com.example.ujkbagas.model.Menu
import com.example.ujkbagas.model.Pesanan
import java.util.Date
import java.util.Locale

class PesananActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityPesananBinding
    private lateinit var mCafeViewModel: CafeViewModel

    private var getPesanan: Menu? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPesananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mCafeViewModel = ViewModelProvider(this).get(CafeViewModel::class.java)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Pesanan"

        getPesanan = intent.getParcelableExtra<Menu>(Key.KEY_MENU_PESANAN_TEMP)

        binding.idbtnLanjut.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.idbtn_lanjut -> {
                val noMeja = binding.idedtNomermeja.text.toString()
                if (noMeja.isNotEmpty()) {
                    val intent = Intent(this, ListPesananActivity::class.java)
                    intent.putExtra(Key.KEY_NO_MEJA, noMeja)

                    val pesanan = getPesanan?.let { Pesanan (0, namaMenu = it.namaMenu, nomerMeja = noMeja, statusPesan = "Belum Dipesan", waktuPesan = getCurrentDate(), hargaMenu = it.hargaMenu) }

                    if (pesanan != null) {
                        mCafeViewModel.insertPesanan(pesanan)
                    }

                    intent.putExtra(Key.KEY_NO_MEJA, noMeja)
                    startActivity(intent)
                } else {
                    binding.idedtNomermeja.error = "Tidak boleh kosong"
                }

            }
        }
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        val date = Date()

        return dateFormat.format(date)
    }
}