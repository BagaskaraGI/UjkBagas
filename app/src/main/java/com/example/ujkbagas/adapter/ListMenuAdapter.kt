package com.example.ujkbagas.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ujkbagas.R
import com.example.ujkbagas.model.Menu

class ListMenuAdapter : RecyclerView.Adapter<ListMenuAdapter.MyViewHolder>() {
    private var listMenu = emptyList<Menu>()

    private lateinit var onItemClickCallback: IOnItemClickCallback

    fun onItemClick(itemClick: IOnItemClickCallback) {
        this.onItemClickCallback = itemClick
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val gambarMenu :ImageView = itemView.findViewById(R.id.idimg_menu)
        val namaMenu : TextView = itemView.findViewById(R.id.idname_menu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_menu, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listMenu.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val menu = listMenu[position]
        holder.namaMenu.text = menu.namaMenu
        Glide.with(holder.gambarMenu)
            .load(menu.gambarMenu)
            .fitCenter()
            .into(holder.gambarMenu)
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(menu)
        }

    }


    fun setData(listMenu: List<Menu>) {
        this.listMenu = emptyList<Menu>()
        this.listMenu = listMenu
        Log.d("List Menu", listMenu.toString())
        notifyDataSetChanged()
    }

    fun deleteData(){
        this.listMenu = emptyList<Menu>()
    }

    interface IOnItemClickCallback {
        fun onItemClicked(menu: Menu)
    }

}