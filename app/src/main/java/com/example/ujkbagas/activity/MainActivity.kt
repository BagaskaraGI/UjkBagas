package com.example.ujkbagas.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.ujkbagas.R
import com.example.ujkbagas.database.CafeViewModel
import com.example.ujkbagas.databinding.ActivityMainBinding
import com.example.ujkbagas.dummyData.dataMenu

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mCafeViewModel: CafeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        mCafeViewModel = ViewModelProvider(this).get(CafeViewModel::class.java)

        mCafeViewModel.insertListMenu(dataMenu)

        setContentView(binding.root)

        supportActionBar?.title = "Bagas Cafe"



        binding.idbtnMenu.setOnClickListener(this)
        binding.idbtnPesanan.setOnClickListener(this)
        binding.idbtnDapur.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.idbtn_menu -> {
                val intent = Intent(this, KategoriActivity::class.java)
                startActivity(intent)
            }
            R.id.idbtn_pesanan -> {
                val intent = Intent(this, PesananActivity::class.java)
                startActivity(intent)
            }
            R.id.idbtn_dapur -> {
                startActivity(Intent(this, DapurActivity::class.java))
            }
        }
    }
}