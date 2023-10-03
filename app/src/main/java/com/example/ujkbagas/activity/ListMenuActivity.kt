package com.example.ujkbagas.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ujkbagas.R
import com.example.ujkbagas.adapter.ListMenuAdapter
import com.example.ujkbagas.database.CafeViewModel
import com.example.ujkbagas.databinding.ActivityListMenuBinding

class ListMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListMenuBinding
    private lateinit var mCafeViewModel: CafeViewModel
    private var getNoMejaPesanan: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mCafeViewModel = ViewModelProvider(this).get(CafeViewModel::class.java)

        val adapter = ListMenuAdapter()
        val rvListMenu = binding.idrvListMenu
        rvListMenu.adapter = adapter
        rvListMenu.layoutManager = LinearLayoutManager(this)

        mCafeViewModel






    }
}