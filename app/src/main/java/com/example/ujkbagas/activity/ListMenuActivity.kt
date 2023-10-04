package com.example.ujkbagas.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ujkbagas.R
import com.example.ujkbagas.adapter.ListMenuAdapter
import com.example.ujkbagas.database.CafeViewModel
import com.example.ujkbagas.databinding.ActivityListMenuBinding
import com.example.ujkbagas.intentKey.Key
import com.example.ujkbagas.model.Menu

class ListMenuActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityListMenuBinding
    private lateinit var mCafeViewModel: CafeViewModel
    private var getNoMejaPesanan: String? = null
    private lateinit var adapterRV: ListMenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mCafeViewModel = ViewModelProvider(this).get(CafeViewModel::class.java)

        getNoMejaPesanan = intent.getStringExtra(Key.KEY_NO_MEJA)
        if (getNoMejaPesanan != null){
            Log.d(Key.KEY_NO_MEJA, getNoMejaPesanan!!)
        }else{
            Log.d(Key.KEY_NO_MEJA, "Null")
        }



        adapterRV = ListMenuAdapter()
        val rvListMenu = binding.idrvListMenu
        rvListMenu.adapter = adapterRV
        rvListMenu.apply {
//            adapter = adapterRV
            adapterRV.onItemClick(object : ListMenuAdapter.IOnItemClickCallback{
                override fun onItemClicked(menu: Menu) {
                    val intent = Intent(this@ListMenuActivity, MenuDetailActivity::class.java)
                    intent.putExtra("Data Menu", menu)
                    intent.putExtra(Key.KEY_NO_MEJA, getNoMejaPesanan)
                    getNoMejaPesanan?.let { Log.d(Key.KEY_NO_MEJA, it) }
                    startActivity(intent)
                }
            })

        }
        rvListMenu.layoutManager = LinearLayoutManager(this)


        val intent = intent
        val kategoriMenu = intent.getStringExtra(Key.KEY_KATEGORI)

        when(kategoriMenu){
            "Makanan" -> {
                mCafeViewModel.readMenuMakananData.observe(this, Observer {
                    adapterRV.setData(it)
                    Log.d("Tes Jumlah data", "${adapterRV.itemCount}")
                })
            }
            "Minuman" -> {
                mCafeViewModel.readMenuMinumanData.observe(this, Observer {
                    adapterRV.setData(it)
                    Log.d("Tes Jumlah data", "${adapterRV.itemCount}")
                })

            }
            "Dessert" -> {
                mCafeViewModel.readMenuDessertData.observe(this, Observer {
                    adapterRV.setData(it)
                    Log.d("Tes Jumlah data", "${adapterRV.itemCount}")
                })

            }
        }

//        mCafeViewModel.readMenuData.observe(this, Observer {
//            adapter.setData(it)
//            Log.d("Tes Jumlah data", "${adapter.itemCount}")
//        })

        binding.idbtnMakanan2.setOnClickListener(this)
        binding.idbtnMinuman2.setOnClickListener(this)
        binding.idbtnDessert2.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.idbtn_makanan -> {
                mCafeViewModel.readMenuMakananData.observe(this, Observer {
                    adapterRV.setData(it)
                    Log.d("Tes Jumlah data", "${adapterRV.itemCount}")
                    adapterRV.notifyDataSetChanged()
                })
                recreate()
            }

            R.id.idbtn_minuman -> {
                mCafeViewModel.readMenuMinumanData.observe(this, Observer {
                    adapterRV.setData(it)
                    Log.d("Tes Jumlah data", "${adapterRV.itemCount}")
                    adapterRV.notifyDataSetChanged()
                })
                recreate()
            }

            R.id.idbtn_dessert -> {
                mCafeViewModel.readMenuDessertData.observe(this, Observer {
                    adapterRV.setData(it)
                    Log.d("Tes Jumlah data", "${adapterRV.itemCount}")
                    adapterRV.notifyDataSetChanged()
                })
                recreate()
            }
        }
    }
}