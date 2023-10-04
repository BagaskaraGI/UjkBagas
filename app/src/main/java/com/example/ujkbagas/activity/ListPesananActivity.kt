package com.example.ujkbagas.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ujkbagas.R
import com.example.ujkbagas.adapter.ListPesananAdapter
import com.example.ujkbagas.database.CafeViewModel
import com.example.ujkbagas.databinding.ActivityListPesananBinding
import com.example.ujkbagas.intentKey.Key
import com.example.ujkbagas.model.Pesanan

class ListPesananActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListPesananBinding
    private lateinit var adapterRV: ListPesananAdapter
    private lateinit var mCafeViewModel: CafeViewModel
    private var getNoMeja: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListPesananBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getNoMeja = intent.getStringExtra(Key.KEY_NO_MEJA)!!
        supportActionBar?.title = "Pesanan No Meja $getNoMeja"

        mCafeViewModel = ViewModelProvider(this).get(CafeViewModel::class.java)

        binding.idbtnKirim.setOnClickListener {
            // Nanti Update dulu
            mCafeViewModel.updatePemesananTemp(getNoMeja!!)

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

        binding.idbtnTambah.setOnClickListener {
            val intent = Intent(this, KategoriActivity::class.java)
            intent.putExtra(Key.KEY_NO_MEJA, getNoMeja)
            startActivity(intent)
        }

        adapterRV = ListPesananAdapter(object : ListPesananAdapter.IOnItemClickCallback {
            override fun onItemClicked(data: Pesanan, position: Int) {
                val dialog = AlertDialog.Builder(this@ListPesananActivity)
                dialog.setTitle("Hapus")
                    .setMessage("Apakah anda yakin menghapus menu ini ?")
                    .setPositiveButton("YA") { _, _ ->
                        //Delete
                        mCafeViewModel.deletePesanan(data)
                        adapterRV.delete(position)
                    }
                    .setNegativeButton("BATAL") { dialog, _ ->
                        dialog.cancel()
                    }
                    .create()
                    .show()
            }
        })

        mCafeViewModel.readPemesananTemp(getNoMeja!!).observe(this, Observer {
            adapterRV.listPesanan = it as ArrayList<Pesanan>
            showListPesanan()
        })

    }


    fun showListPesanan() {
        binding.idrvListpesanan.apply {
            adapter = adapterRV
            layoutManager = LinearLayoutManager(this@ListPesananActivity)
        }
        var total = 0
        for (item in adapterRV.listPesanan) {
            total += item.hargaMenu.toInt()
        }
        binding.idtvTotalharga.text = "Total harga $total"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}