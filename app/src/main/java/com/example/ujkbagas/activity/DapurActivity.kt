package com.example.ujkbagas.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ujkbagas.adapter.ListDapurAdapter
import com.example.ujkbagas.database.CafeViewModel
import com.example.ujkbagas.databinding.ActivityDapurBinding
import com.example.ujkbagas.model.Pesanan

class DapurActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDapurBinding
    private lateinit var mCafeViewModel: CafeViewModel
    private lateinit var adapterRV: ListDapurAdapter


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        binding = ActivityDapurBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Dapur"

        adapterRV = ListDapurAdapter(object : ListDapurAdapter.IOnItemClickCallback {
            override fun onItemClicked(data: Pesanan, position: Int) {
                showDialog(data, position)
            }
        })

        mCafeViewModel = ViewModelProvider(this).get(CafeViewModel::class.java)

        mCafeViewModel.readPesananDapur.observe(this, Observer {
            adapterRV.listDapur = it as ArrayList<Pesanan>
            showRvDapur()
        })

    }


    fun showRvDapur() {
        binding.idrvDapur.apply {
            adapter = adapterRV
            layoutManager = LinearLayoutManager(this@DapurActivity)
        }
    }


    fun showDialog(data: Pesanan, position: Int) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Confirmasi")
            .setMessage("Apakah menu sudah siap ?")
            .setPositiveButton("Sudah") { _, _ ->
                // Delete pesanan
                mCafeViewModel.deletePesanan(data)
                adapterRV.delete(position)
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.cancel()
            }
            .create()
            .show()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }


}